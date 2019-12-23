package ua.com.parkhub.service;

import org.junit.jupiter.api.Test;
import org.junit.Assert;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import ua.com.parkhub.model.AddressModel;
import ua.com.parkhub.model.ParkingModel;
import ua.com.parkhub.persistence.entities.Address;
import ua.com.parkhub.persistence.entities.Parking;
import ua.com.parkhub.persistence.entities.Slot;
import ua.com.parkhub.persistence.impl.ParkingDAO;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
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

//    @Test
//    public void checkCorrectReturnCityBuildStreetFindAddress(){
//        Parking parking = new Parking();
//        Address address = new Address();
//        address.setBuilding("build");
//        address.setStreet("Street");
//        address.setCity("City");
//        parking.setAddress(address);
//        ParkingDAO parkingDAO = mock(ParkingDAO.class);
//        ParkingService parkingService = new ParkingService(parkingDAO);
//        Assert.assertTrue(parkingService.findAddress(parking).contains("City"));
//        Assert.assertTrue(parkingService.findAddress(parking).contains("build"));
//        Assert.assertTrue(parkingService.findAddress(parking).contains("Street"));
//    }

//
//    @Test
//    public void checkFindParkingById(){
//        Parking parking = new Parking();
//        parking.setSlotsNumber(2);
//        Slot slot = new Slot();
//        slot.setReserved(true);
//        Slot slot1 = new Slot();
//        slot1.setReserved(false);
//        List<Slot> slots = new ArrayList<>();
//        slots.add(slot);
//        slots.add(slot1);
//        parking.setSlots(slots);
//        parking.setId((long) 1);
//        Address address = new Address();
//        address.setBuilding("build");
//        address.setStreet("Street");
//        address.setCity("City");
//        parking.setAddress(address);
//        parking.setParkingName("parking1");
//        parking.setTariff(25);
//        ParkingDAO parkingDAO = mock(ParkingDAO.class);
//        when(parkingDAO.findElementById(anyLong())).thenReturn(parking);
//        ParkingService parkingService = new ParkingService(parkingDAO);
//        Assert.assertTrue(parkingService.findParkingById(1).getParkingName().equals(parking.getParkingName()));
//        Assert.assertTrue(parkingService.findParkingById(1).getFullness().equals("1/2"));
//    }

//    @Test
//    public void checkFindAllParking(){
//        Parking parking = new Parking();
//        parking.setSlotsNumber(2);
//        Slot slot = new Slot();
//        slot.setReserved(true);
//        Slot slot1 = new Slot();
//        slot1.setReserved(false);
//        List<Slot> slots = new ArrayList<>();
//        slots.add(slot);
//        slots.add(slot1);
//        parking.setSlots(slots);
//        parking.setId((long) 1);
//        Address address = new Address();
//        address.setBuilding("build");
//        address.setStreet("Street");
//        address.setCity("City");
//        parking.setAddress(address);
//        parking.setParkingName("parking1");
//        parking.setTariff(25);
//        ParkingDAO parkingDAO = mock(ParkingDAO.class);
//        List<Parking> parkings = new ArrayList<>();
//        parkings.add(parking);
//        when(parkingDAO.findAll()).thenReturn(parkings);
//        ParkingService parkingService = new ParkingService(parkingDAO);
//        Assert.assertTrue(parkingService.findAllParking().get(0).getParkingName().equals(parking.getParkingName()));
//        Assert.assertTrue(parkingService.findAllParking().get(0).getFullness().equals("1/2"));
//
//    }

//    @Test
//    public void checkFindFullness(){
//        Parking parking = new Parking();
//        parking.setSlotsNumber(2);
//        Slot slot = new Slot();
//        slot.setReserved(true);
//        Slot slot1 = new Slot();
//        slot1.setReserved(false);
//        List<Slot> slots = new ArrayList<>();
//        slots.add(slot);
//        slots.add(slot1);
//        parking.setSlots(slots);
//        ParkingDAO parkingDAO = mock(ParkingDAO.class);
//        ParkingService parkingService = new ParkingService(parkingDAO);
//        Assert.assertTrue(parkingService.findFullness(parking).contains("1/2"));
//    }

}