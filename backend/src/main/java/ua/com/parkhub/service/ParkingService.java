package ua.com.parkhub.service;

import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.com.parkhub.mapper.AddressMapper;
import ua.com.parkhub.mapper.ParkingMapper;
import ua.com.parkhub.model.AddressModel;
import ua.com.parkhub.model.ParkingModel;
import ua.com.parkhub.model.ParkingRequestModel;
import ua.com.parkhub.persistence.entities.*;
import ua.com.parkhub.persistence.impl.*;

@Service
@Transactional
public class ParkingService {

    private final ParkingDAO parkingDAO;
    private final AddressDAO addressDAO;
    private final UserDAO userDAO;
    private final AddressMapper addressMapper;
    private final ParkingMapper parkingMapper;

    @Autowired
    public ParkingService(ParkingDAO parkingDAO, AddressDAO addressDAO,UserDAO userDAO) {
        this.parkingDAO = parkingDAO;
        this.addressDAO = addressDAO;
        this.userDAO = userDAO;
        addressMapper = Mappers.getMapper( AddressMapper.class);
        parkingMapper = Mappers.getMapper( ParkingMapper.class);
    }

    public boolean isParkingNameUnique(ParkingRequestModel parkingRequestModel) {
        Long count = parkingDAO.
                countOfParkingsByName(parkingRequestModel.getParkingName());
        return count == 0;
    }

    public boolean checkIfAddressIsUnique(ParkingRequestModel parkingRequestModel) {
        Long count = parkingDAO.
                countOfParkingsByAddress(parkingRequestModel.getCity(),parkingRequestModel.getStreet(),parkingRequestModel.getBuilding());
        return count == 0;
    }

    public ParkingModel createParkingModelFROMParkingRequestModel(ParkingRequestModel parkingRequestModel) {
        ParkingModel parkingModel = new ParkingModel();
        parkingModel.setParkingName(parkingRequestModel.getParkingName());
        parkingModel.setSlotsNumber(parkingRequestModel.getSlotsNumber());
        parkingModel.setTariff(parkingRequestModel.getTariff());
        return parkingModel;
    }

    public AddressModel createAddressModelFROMParkingRequestModel(ParkingRequestModel parkingRequestModel) {
        AddressModel addressModel = new AddressModel();
        addressModel.setBuilding(parkingRequestModel.getBuilding());
        addressModel.setCity(parkingRequestModel.getCity());
        addressModel.setStreet(parkingRequestModel.getStreet());
        return addressModel;
    }

    public void createParkingFromParkingAndAddressModel(AddressModel addressModel, ParkingModel parkingModel, long id) {
        Address address = addressMapper.addressModelToAddress(addressModel);
        Parking parking = parkingMapper.parkingModelToParking(parkingModel);
        parking.setOwner(userDAO.findElementById(id));
        parking.setAddress(address);
        addressDAO.addElement(address);
        parkingDAO.addElement(parking);
    }


}
