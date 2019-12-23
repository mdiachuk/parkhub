package ua.com.parkhub.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;

import org.mockito.junit.jupiter.MockitoExtension;
import ua.com.parkhub.model.AddressModel;
import ua.com.parkhub.model.ParkingModel;
import ua.com.parkhub.persistence.impl.ParkingDAO;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class ParkingServiceTest {

    @Mock
    private ParkingDAO parkingDAOMock;

    @Spy
    private AddressModel addressModelSpy;

    @Spy
    ParkingModel parkingModelSpy;

    @InjectMocks
    private ParkingService parkingService;

    @Test
    void checkIfParkingIsUnique() {
        parkingModelSpy.setParkingName("parking1");
        when(parkingDAOMock.countOfParkingsByName(parkingModelSpy.getParkingName())).thenReturn(0L);
        assertTrue(parkingService.isParkingNameUnique(parkingModelSpy),"The parkingName should be unique ");
    }

    @Test
    void checkIfParkingIsNotUnique() {
        parkingModelSpy.setParkingName("parking1");
        when(parkingDAOMock.countOfParkingsByName("parking1")).thenReturn(1L);
        assertFalse(parkingService.isParkingNameUnique(parkingModelSpy),"The parkingName should be unique ");
    }

    @Test
    void checkIfAddressIsUnique() {
        parkingModelSpy.setAddressModel(addressModelSpy);
        when(parkingDAOMock.countOfParkingsByAddress(addressModelSpy)).thenReturn(0L);
        assertTrue(parkingService.checkIfAddressIsUnique(parkingModelSpy),"The address should be unique ");
    }



}