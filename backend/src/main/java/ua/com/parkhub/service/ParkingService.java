package ua.com.parkhub.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.parkhub.mappers.ParkingMapper;
import ua.com.parkhub.model.ParkingModel;
import ua.com.parkhub.persistence.impl.ParkingDAO;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class ParkingService {

    private ParkingDAO parkingDAO;
    private ParkingMapper parkingMapper;

    @Autowired
    public ParkingService(ParkingDAO parkingDAO, ParkingMapper parkingMapper) {
        this.parkingDAO = parkingDAO;
        this.parkingMapper = parkingMapper;
    }

    public List<ParkingModel> findAll(){

        return parkingDAO.findAll().stream().map(parkingMapper::fromEntityToModel).collect(Collectors.toList());
    }
}
