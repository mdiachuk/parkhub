package ua.com.parkhub.service.impl;

import org.hibernate.Hibernate;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.com.parkhub.exceptions.ParkHubException;
import ua.com.parkhub.persistence.entities.Parking;
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
    public ua.com.parkhub.model.Parking findParkingByIdWithSlotList(long id) {
        Parking parking = parkingDAO.findElementById(id)
                .orElseThrow(() -> new ParkHubException("No Parking found with id " + id));
        Hibernate.initialize(parking.getSlots());
        if (parking.isActive()) {
            return mapper.map(parking, ua.com.parkhub.model.Parking.class);
        }
        throw new ParkHubException("Unfortunately this parking is temporary unavailable");
    }

    @Transactional(readOnly = true)
    public List<ua.com.parkhub.model.Parking> findAllParking() {
        List<Parking> parkingList = parkingDAO.findAll();
        if (parkingList.isEmpty()) {
            throw new ParkHubException("Unfortunately all parkings are temporary unavailable");
        }
        return parkingList.stream()
                .map(parkingEntity -> mapper.map(parkingEntity, ua.com.parkhub.model.Parking.class))
                .collect(Collectors.toList());
    }
}
