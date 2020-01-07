package ua.com.parkhub.service;

import java.util.List;

public interface IParkingService {

    Parking findParkingByIdWithSlotList(long id);
    List<Parking> findAllParking();
}
