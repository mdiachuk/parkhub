package ua.com.parkhub.service.impl;

import org.hibernate.Hibernate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.com.parkhub.exceptions.*;
import ua.com.parkhub.model.*;
import ua.com.parkhub.exceptions.ParkHubException;
import ua.com.parkhub.exceptions.ParkingDoesntExistException;
import ua.com.parkhub.exceptions.StatusCode;
import ua.com.parkhub.model.comparator.SlotComparator;
import ua.com.parkhub.persistence.impl.AddressDAO;
import ua.com.parkhub.persistence.impl.ParkingDAO;
import ua.com.parkhub.persistence.impl.UserDAO;
import ua.com.parkhub.service.IParkingService;

import java.lang.reflect.Field;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class  ParkingService implements IParkingService {

    private static final Logger logger = LoggerFactory.getLogger(ParkingService.class);

    private ParkingDAO parkingDAO;
    private final AddressDAO addressDAO;
    private final UserDAO userDAO;
    private final BookingService bookingService;
    private final SlotComparator slotComparator;
    private final AddressGeoService addressGeoService;

    @Autowired
    public ParkingService(ParkingDAO parkingDAO, AddressDAO addressDAO, UserDAO userDAO, BookingService bookingService, SlotComparator slotComparator, AddressGeoService addressGeoService) {
        this.parkingDAO = parkingDAO;
        this.addressDAO = addressDAO;
        this.userDAO = userDAO;
        this.bookingService = bookingService;
        this.slotComparator = slotComparator;
        this.addressGeoService = addressGeoService;
    }

    @Override
    public boolean isParkingNameUnique(ParkingModel parkingRequestModel) {
        Long count = parkingDAO.
                countOfParkingsByName(parkingRequestModel.getInfo().getParkingName());
        return count == 0;
    }

    @Override
    public boolean checkIfAddressIsUnique(ParkingModel parkingRequestModel) {
        Long count = parkingDAO.
                countOfParkingsByAddress(parkingRequestModel.getInfo().getAddressModel());
        return count == 0;
    }

    @Transactional
    @Override
    public void createParkingByOwnerID(ParkingModel parkingModel, long id) {
        logger.info("Creating parking");
        if(!checkIfAddressIsUnique(parkingModel)){
            logger.warn("Parking with such address already exists");
            throw new AddressException("This address already exists");
        }
        if(!isParkingNameUnique(parkingModel)){
            logger.warn("Parking with such name already exists");
            throw new ExistingParkingException("This parking already exists");
        }
        parkingModel.getInfo().setOwner(userDAO.findElementById(id).orElseThrow(() ->
                new NotFoundInDataBaseException("Manager not found")));
        parkingModel.getInfo().setAddressModel(createAddressModel(parkingModel));
        parkingDAO.addElement(createParkingWithSlots(parkingModel));
        logger.info("Parking created successfully");
    }

    @Override
    public AddressModel createAddressModel(ParkingModel parkingModel) {
        logger.info("Creating address");
        AddressModel address = parkingModel.getInfo().getAddressModel();
        address = setLatLan(address);
        logger.info("Address created");
        return addressDAO.addWithResponse(address);
    }

    @Override
    public ParkingModel createParkingWithSlots(ParkingModel parkingModel) {
        logger.info("Creating slots");
        List<SlotModel> slotModels = new ArrayList<>();
        IntStream.rangeClosed(1, parkingModel.getInfo().getSlotsNumber()).forEach(i -> {
                    SlotModel slotModel = new SlotModel();
                    slotModel.setSlotNumber("A" + i);
                    slotModels.add(slotModel);
                }
        );
        parkingModel.setSlots(slotModels);
        logger.info("Slots created");
        return parkingModel;
    }

    public ParkingModel findParkingById(Long id) {
        return parkingDAO.findElementById(id).orElseThrow(() -> new ParkingDoesntExistException(StatusCode.PARKING_DOESNT_EXIST));
    }


    public List<ParkingModel> findAllParking() {

        return parkingDAO.findAll();
    }

    public List<ParkingModel> findAllParkingByOwnerId(Long id) {
        return parkingDAO.findAllParkingByOwnerId(id);
    }

    @Transactional(readOnly = true)
    public ParkingModel findParkingByIdWithSlotList(long id) {
        ParkingModel parking = parkingDAO.findElementById(id)
                .orElseThrow(() -> new ParkHubException("No Parking found with id " + id));
        Hibernate.initialize(parking.getSlots());
        if (parking.getInfo().isActive()) {
            return parking;
        }
        throw new ParkHubException("Unfortunately this parking is temporary unavailable");
    }

    @Override
    public ParkingModel findParkingByIdWithSlotListAndDateRange(long id, long checkIn, long checkOut) {
        ParkingModel parkingModel = findParkingByIdWithSlotList(id);
        //TODO if null condition
        List<SlotModel> slotList = parkingModel.getSlots();
        for (SlotModel slotModel : slotList) {
            Optional<BookingModel> bookingModel = bookingService.findBookingByIdAndDateTimeRange(slotModel.getId(), checkIn, checkOut);
            //TODO refactoring isPresent change to exception
            if (bookingModel.isPresent()) {
                slotModel.setReserved(true);
            }
        }
        slotList.sort(slotComparator);
        return parkingModel;
    }

    public List<String> getNotEmptyFieldNamesFromSpecificParking(ParkingInfoModel parkingModelParam) {

        Field[] paramFields = parkingModelParam.getClass().getDeclaredFields();

        return Arrays.stream(paramFields).filter(field -> {
            try {
                field.setAccessible(true);
                Object value = field.get(parkingModelParam);
                if (value instanceof Integer) {
                    return (Integer) value != 0;
                } else {
                    return value != null;
                }
            } catch (IllegalAccessException ex) {
                logger.info("Field access denied");
                return false;
            }
        }).map(Field::getName).collect(Collectors.toList());
    }

    @Transactional
    public void updateParking(Long id, ParkingModel parkingModelParam) {

        ParkingModel parkingModel = findParkingById(id);
        List<String> fieldsNameList = getNotEmptyFieldNamesFromSpecificParking(parkingModelParam.getInfo());

        fieldsNameList.forEach(fieldName -> {
            try {
                Field fieldParam = parkingModelParam.getInfo().getClass().getDeclaredField(fieldName);
                Field field = parkingModel.getInfo().getClass().getDeclaredField(fieldName);
                field.setAccessible(true);
                fieldParam.setAccessible(true);
                field.set(parkingModel.getInfo(), fieldParam.get(parkingModelParam.getInfo()));
            } catch (NoSuchFieldException | IllegalAccessException e) {
                logger.info("There is no field");
            }
        });

        if (fieldsNameList.contains("addressModel")) {
            AddressModel address = addressDAO.addWithResponse(setLatLan(parkingModelParam.getInfo().getAddressModel()));
            parkingModel.getInfo().setAddressModel(address);
        }

        parkingDAO.updateElement(parkingModel);
    }


    @Override
    public AddressModel setLatLan(AddressModel addressModel) {

        String address = addressModel.getCity() + "," +
                addressModel.getStreet() + " " +
                addressModel.getBuilding();

        Map<String, String> latLon = addressGeoService.getLatLon(address);

        addressModel.setLat(latLon.get("lat"));
        addressModel.setLon(latLon.get("lon"));

        return addressModel;
    }

    public List<ParkingModel> findAllParkingModel() {
        return parkingDAO.findAll();
    }


    public List<ParkingModel> findParkingInArea(String address) {

        Map<String, String> map = addressGeoService.getLatLon(address);

        return findAllParkingModel().stream()
                .filter(x -> (addressGeoService
                        .enteringTheRadius(map.get("lat"), map.get("lon"),
                                x.getInfo().getAddressModel().getLat(), x.getInfo().getAddressModel().getLon())))
                .collect(Collectors.toList());
    }
}
