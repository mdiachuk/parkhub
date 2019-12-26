package ua.com.parkhub.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.com.parkhub.mappers.toModel.ParkingEntityToModelMapper;
import ua.com.parkhub.model.ParkingModel;
import ua.com.parkhub.persistence.entities.Parking;
import ua.com.parkhub.persistence.impl.ParkingDAO;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@Transactional
public class ParkingService {

    private ParkingDAO parkingDAO;
    private ParkingEntityToModelMapper parkingEntityToModelMapper;

    @Autowired
    public ParkingService(ParkingDAO parkingDAO, ParkingEntityToModelMapper parkingEntityToModelMapper) {
        this.parkingDAO = parkingDAO;
        this.parkingEntityToModelMapper = parkingEntityToModelMapper;
    }

    public List<ParkingModel> findAllParking(){

        return parkingDAO.findAll().stream().map(parkingEntityToModelMapper::transform).collect(Collectors.toList());
    }

    public Optional<ParkingModel> findParkingById(long id){

        Optional<Parking> parking = parkingDAO.findElementById(id);
        ParkingModel parkingModel;

        parkingModel = parking.map(value -> parkingEntityToModelMapper.transform(value)).orElse(null);
        return Optional.ofNullable(parkingModel);
    }
}
