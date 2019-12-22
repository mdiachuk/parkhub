package ua.com.parkhub.service.impl;

import org.hibernate.Hibernate;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.com.parkhub.exceptions.ParkHubException;
import ua.com.parkhub.model.Parking;
import ua.com.parkhub.persistence.entities.ParkingEntity;
import ua.com.parkhub.persistence.impl.ParkingDAO;
import ua.com.parkhub.service.IParkingService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ParkingService implements IParkingService {
    private final ParkingDAO parkingDAO;
    private final ModelMapper mapper;

    @Autowired
    public ParkingService(ParkingDAO parkingDAO, ModelMapper mapper) {
        this.parkingDAO = parkingDAO;
        this.mapper = mapper;
    }

    @Transactional(readOnly = true)
    public Parking findParkingByIdWithSlotList(long id) {
        ParkingEntity parkingEntity = parkingDAO.findElementById(id)
                .orElseThrow(()->new ParkHubException("No Parking found with id " + id));
        Hibernate.initialize(parkingEntity.getSlots());
        System.out.println(parkingEntity);
        return mapper.map(parkingEntity, Parking.class);
    }

    @Transactional(readOnly = true)
    public List<Parking> findAllParking() {
        List<ParkingEntity> parkingList = parkingDAO.findAll();
        if(parkingList.isEmpty()) {
            //TODO
        }
        return parkingList.stream()
                .map(parkingEntity -> mapper.map(parkingEntity, Parking.class))
                .collect(Collectors.toList());
    }
}
