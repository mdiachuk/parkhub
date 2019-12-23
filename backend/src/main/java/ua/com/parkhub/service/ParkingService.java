package ua.com.parkhub.service;

import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.com.parkhub.dto.ShortParkingDTO;
import ua.com.parkhub.mapper.ParkingRequestMapper;
import ua.com.parkhub.mappers.ParkingMapper;
import ua.com.parkhub.model.ParkingModel;
import ua.com.parkhub.persistence.entities.*;
import ua.com.parkhub.persistence.impl.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ParkingService {

    private final ParkingDAO parkingDAO;
    private final AddressDAO addressDAO;
    private ParkingMapper parkingMapper;
    private final UserDAO userDAO;
    private final ParkingRequestMapper parkingRequestMapper;

    @Autowired
    public ParkingService(ParkingDAO parkingDAO, AddressDAO addressDAO,UserDAO userDAO, ParkingMapper parkingMapper) {
        this.parkingDAO = parkingDAO;
        this.addressDAO = addressDAO;
        this.parkingMapper = parkingMapper;
        this.userDAO = userDAO;
        parkingRequestMapper = Mappers.getMapper( ParkingRequestMapper.class);
    }

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

    public void createParkingByOwnerID(ParkingModel parkingModel, long id) {
        Address address = parkingRequestMapper.parkingModelToAddress(parkingModel);
        Parking parking = parkingRequestMapper.parkingModelToParking(parkingModel);
        parking.setOwner(userDAO.findElementById(id));
        parking.setAddress(address);
        addressDAO.addElement(address);
        parkingDAO.addElement(parking);
    }

    public List<ParkingModel> findAll(){
        List<ParkingModel> parkings= new ArrayList<>();
        parkings=  parkingDAO.findAll().stream().map(parkingMapper::fromEntityToModel).collect(Collectors.toList());
        parkings.size();
        return parkingDAO.findAll().stream().map(parkingMapper::fromEntityToModel).collect(Collectors.toList());
    }

    public ParkingModel findParkingByIdYaroslav(long id){

        return parkingMapper.fromEntityToModel(parkingDAO.findElementById(id));
    }
}
