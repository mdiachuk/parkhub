package ua.com.parkhub.service.impl;

import org.hibernate.Hibernate;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.com.parkhub.exceptions.ParkHubException;
import ua.com.parkhub.model.AddressModel;
import ua.com.parkhub.model.ParkingModel;
import ua.com.parkhub.persistence.impl.AddressDAO;
import ua.com.parkhub.persistence.impl.ParkingDAO;
import ua.com.parkhub.persistence.impl.UserDAO;
import ua.com.parkhub.service.IParkingService;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ParkingService implements IParkingService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ParkingService.class);


    private ParkingDAO parkingDAO;
    private final AddressDAO addressDAO;
    private final UserDAO userDAO;
    private final AddressGeoService addressGeoService;

    private final ModelMapper mapper;

    @Autowired
    public ParkingService(ParkingDAO parkingDAO, AddressDAO addressDAO,
                          UserDAO userDAO,
                          AddressGeoService addressGeoService, ModelMapper mapper
    ) {
        this.parkingDAO = parkingDAO;
        this.addressDAO = addressDAO;
        this.userDAO = userDAO;
        this.addressGeoService = addressGeoService;
        this.mapper = mapper;
    }

    @Transactional(readOnly = true)
    public ParkingModel findParkingByIdWithSlotList(long id) {
        ParkingModel parking = parkingDAO.findElementById(id)
                .orElseThrow(() -> new ParkHubException("No Parking found with id " + id));
        Hibernate.initialize(parking.getSlots());
        if (parking.isActive()) {

            return mapper.map(parking, ParkingModel.class);
        }
        throw new ParkHubException("Unfortunately this parking is temporary unavailable");

    }

    @Transactional(readOnly = true)
    public List<ParkingModel> findAllParking() {
        List<ParkingModel> parkingList = parkingDAO.findAll();
        if (parkingList.isEmpty()) {
            throw new ParkHubException("Unfortunately all parkings are temporary unavailable");
        }
        return parkingList.stream()
                .map(parkingEntity -> mapper.map(parkingEntity, ParkingModel.class))
                .collect(Collectors.toList());
    }


    /**
     * New
     */

    public boolean isParkingNameUnique(ParkingModel parkingRequestModel) {
        Long count = parkingDAO.
                countOfParkingsByName(parkingRequestModel.getParkingName());
        return count == 0;
    }

    public boolean checkIfAddressIsUnique(ParkingModel parkingRequestModel) {
        Long count = parkingDAO.
                countOfParkingsByAddress(parkingRequestModel.getAddressModel());
        return count == 0;
    }

    @Transactional
    public void createParkingByOwnerID(ParkingModel parkingModel, long id) {

        AddressModel address = parkingModel.getAddressModel();
        address = setLatLan(address);

        if (userDAO.findElementById(id).isPresent()) {
            parkingModel.setOwner(userDAO.findElementById(id).get());
        }
        addressDAO.addElement(address);
        AddressModel addressModel = addressDAO.addWithResponse(address);
        parkingModel.setAddressModel(addressModel);
        parkingModel.setId(null);
        parkingDAO.addElement(parkingModel);
    }

    private AddressModel setLatLan(AddressModel addressModel) {

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

    public List<ParkingModel> findAllParkingModel() {
        return parkingDAO.findAll();
    }

    public Optional<ParkingModel> findParkingById(long id) {

        return parkingDAO.findElementById(id);
    }

}
