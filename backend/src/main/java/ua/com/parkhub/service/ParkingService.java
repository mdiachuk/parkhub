package ua.com.parkhub.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.com.parkhub.mappers.entityToModel.ParkingEntityToModelMapper;
import ua.com.parkhub.model.ParkingModel;
import ua.com.parkhub.persistence.impl.ParkingDAO;

import java.util.List;
import java.util.Optional;


@Service
@Transactional
public class ParkingService {

    private ParkingDAO parkingDAO;

    @Autowired
    public ParkingService(ParkingDAO parkingDAO, ParkingEntityToModelMapper parkingEntityToModelMapper) {
        this.parkingDAO = parkingDAO;
    }

    public List<ParkingModel> findAllParking(){

        return parkingDAO.findAll();
    }

    public Optional<ParkingModel> findParkingById(long id){

        return  parkingDAO.findElementById(id);
    }
}
