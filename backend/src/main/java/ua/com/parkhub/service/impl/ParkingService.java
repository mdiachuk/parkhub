package ua.com.parkhub.service.impl;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.com.parkhub.exceptions.ParkHubException;
import ua.com.parkhub.exceptions.ParkingDoesntExistException;
import ua.com.parkhub.exceptions.StatusCode;
import ua.com.parkhub.model.AddressModel;
import ua.com.parkhub.model.BookingModel;
import ua.com.parkhub.model.ParkingModel;
import ua.com.parkhub.model.SlotModel;
import ua.com.parkhub.model.comparator.SlotComparator;
import ua.com.parkhub.persistence.impl.AddressDAO;
import ua.com.parkhub.persistence.impl.ParkingDAO;
import ua.com.parkhub.persistence.impl.UserDAO;
import ua.com.parkhub.service.IParkingService;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ParkingService implements IParkingService  {


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
                countOfParkingsByName(parkingRequestModel.getParkingName());
        return count == 0;
    }

    @Override
    public boolean checkIfAddressIsUnique(ParkingModel parkingRequestModel) {
        Long count = parkingDAO.
                countOfParkingsByAddress(parkingRequestModel.getAddressModel());
        return count == 0;
    }

    @Transactional
    @Override
    public void createParkingByOwnerID(ParkingModel parkingModel, long id) {
        AddressModel address = parkingModel.getAddressModel();
        address = setLatLan(address);
        if (userDAO.findElementById(id).isPresent()) {
            parkingModel.setOwner(userDAO.findElementById(id).get());}
        AddressModel addressModel = addressDAO.addWithResponse(address);
        parkingModel.setAddressModel(addressModel);
        parkingDAO.addElement(parkingModel);
    }

    public List<ParkingModel> findAllParkingModel(){
        return parkingDAO.findAll();
    }

    public ParkingModel findParkingById(long id) {
        return parkingDAO.findElementById(id).orElseThrow(() -> new ParkingDoesntExistException(StatusCode.PARKING_DOESNT_EXIST));
    }


    public List<ParkingModel> findAllParking() {

        return parkingDAO.findAll();
    }

    @Transactional(readOnly = true)
    public ParkingModel findParkingByIdWithSlotList(long id) {
        ParkingModel parking = parkingDAO.findElementById(id)
                .orElseThrow(() -> new ParkHubException("No Parking found with id " + id));
        Hibernate.initialize(parking.getSlots());
        if (parking.isActive()) {
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
                System.out.println(bookingModel.get());
                slotModel.setReserved(true);
            }
        }
        slotList.sort(slotComparator);
        System.out.println("Parking model : " + parkingModel);
        return parkingModel;
    }

    @Transactional
    public void updateParking(Long id, ParkingModel parkingModelParam) {
        ParkingModel parkingModel = findParkingById(id);
        AddressModel addressModel = parkingModel.getAddressModel();
        addressModel = setLatLan(addressModel);
        parkingModel.setAddressModel(addressModel);
        Field[] paramFields = parkingModelParam.getClass().getDeclaredFields();
        List<String> fieldsNameList = Arrays.stream(paramFields).filter(field -> {
            try {
                field.setAccessible(true);
                Object value = field.get(parkingModelParam);
                if (value instanceof Integer){
                    return (Integer) value != 0;
                }
                else {
                    return value != null;
                }
            } catch (IllegalAccessException ex) {
                return false;
            }
        }).map(Field::getName).collect(Collectors.toList());

        for (String name : fieldsNameList) {
            try {
                Field fieldParam = parkingModelParam.getClass().getDeclaredField(name);
                Field field = parkingModel.getClass().getDeclaredField(name);
                field.setAccessible(true);
                fieldParam.setAccessible(true);
                field.set(parkingModel, fieldParam.get(parkingModelParam));
            } catch (NoSuchFieldException | IllegalAccessException e) {
                e.printStackTrace();
            }

            if (fieldsNameList.contains("addressModel")) {
                AddressModel a = addressDAO.addWithResponse(parkingModelParam.getAddressModel()); //from non id model gets model with id
                parkingModel.setAddressModel(a);
            }

            parkingDAO.updateElement(parkingModel);
        }
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

    public List<ParkingModel> findParkingInArea(String address) {

        Map<String, String> map = addressGeoService.getLatLon(address);

        return findAllParkingModel().stream()
                .filter(x -> (addressGeoService
                        .enteringTheRadius(map.get("lat"), map.get("lon"),
                                x.getAddressModel().getLat(), x.getAddressModel().getLon())) == true)
                .collect(Collectors.toList());
    }

}
