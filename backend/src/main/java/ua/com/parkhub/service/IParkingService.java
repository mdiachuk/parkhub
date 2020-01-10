package ua.com.parkhub.service;

import ua.com.parkhub.model.ParkingModel;

import java.util.List;

public interface IParkingService {

    ParkingModel findParkingByIdWithSlotList(long id);
    List<ParkingModel> findAllParking();
}
