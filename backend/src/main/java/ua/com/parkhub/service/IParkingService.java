package ua.com.parkhub.service;

import ua.com.parkhub.model.Parking;

import java.util.List;

public interface IParkingService {

    Parking findParkingByIdWithSlotList(long id);
    List<Parking> findAllParking();
}
