package ua.com.parkhub.service;

import ua.com.parkhub.model.AddressModel;
import ua.com.parkhub.model.ParkingModel;

import java.util.Arrays;
import java.util.List;


public interface IParkingService {

    List<ParkingModel> findAllParking();
    ParkingModel findParkingByIdWithSlotList(long id);
    ParkingModel findParkingByIdWithSlotListAndDateRange(long id, long checkIn, long checkOut);
    List<ParkingModel> findParkingInArea(String address);
    boolean isParkingNameUnique(ParkingModel parkingRequestModel);
    boolean checkIfAddressIsUnique(ParkingModel parkingRequestModel);
    void createParkingByOwnerID(ParkingModel parkingModel, long id);
    AddressModel setLatLan(AddressModel addressModel);
    List<ParkingModel> findAllParkingByOwnerId(Long parkingId);
    ParkingModel findParkingById(Long Id);
    void updateParking(Long parkingId, ParkingModel transform);
}

