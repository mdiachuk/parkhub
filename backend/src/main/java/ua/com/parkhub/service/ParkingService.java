package ua.com.parkhub.service;

import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.com.parkhub.mapper.AddressMapper;
import ua.com.parkhub.mapper.ParkingMapper;
import ua.com.parkhub.model.ParkingModel;
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

    public boolean isParkingNameUnique(ParkingModel parkingRequestModel) {
        Long count = parkingDAO.
                countOfParkingsByName(parkingRequestModel.getParkingName());
        return count == 0;
    }

    public boolean checkIfAddressIsUnique(ParkingModel parkingRequestModel) {
        Long count = parkingDAO.
                countOfParkingsByAddress(parkingRequestModel.getAddress());
        return count == 0;
    }

    public void createParkingByOwnerID(ParkingModel parkingModel, long id) {
        Address address = addressMapper.parkingModelToAddress(parkingModel);
        Parking parking = parkingMapper.parkingModelToParking(parkingModel);
        parking.setOwner(userDAO.findElementById(id));
        parking.setAddress(address);
        addressDAO.addElement(address);
        parkingDAO.addElement(parking);
    }


}
