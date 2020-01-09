package ua.com.parkhub.service;

import ua.com.parkhub.persistence.entities.Parking;

import java.util.List;

public interface IParkingService {

    Parking findParkingByIdWithSlotList(long id);
    List<Parking> findAllParking();
}
