package ua.com.parkhub.service;

import ua.com.parkhub.model.ParkingModel;

import java.util.List;
import java.util.Optional;

public interface IParkingService {

    Optional<ParkingModel> findParkingById(long id);
    List<ParkingModel> findAllParking();
    void updateParking(Long id, ParkingModel parkingModelParam) throws NoSuchFieldException, IllegalAccessException;
    ParkingModel findParkingByIdWithSlotList(long id);
    ParkingModel findParkingByIdWithSlotListAndDateRange(long id, String checkIn, String checkOut);

}
