package ua.com.parkhub.service;

import org.junit.jupiter.api.Test;
import org.junit.Assert;
import ua.com.parkhub.dto.ParkingDTO;
import ua.com.parkhub.persistence.entities.Address;
import ua.com.parkhub.persistence.entities.Parking;
import ua.com.parkhub.persistence.entities.Slot;
import ua.com.parkhub.persistence.entities.User;
import ua.com.parkhub.persistence.impl.ParkingDAO;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ParkingServiceTest {

    @Test
    public void checkCorrectReturnCityBuildStreetFindAddress(){
        Parking parking = new Parking();
        Address address = new Address();
        address.setId((long) 1);
        address.setBuilding("build");
        address.setStreet("Street");
        address.setCity("City");
        parking.setAddress(address);
        ParkingDAO parkingDAO = mock(ParkingDAO.class);
        ParkingService parkingService = new ParkingService(parkingDAO);
        System.out.println(parkingService.findAddress(parking));
        Assert.assertTrue(parkingService.findAddress(parking).contains("City"));
        Assert.assertTrue(parkingService.findAddress(parking).contains("build"));
        Assert.assertTrue(parkingService.findAddress(parking).contains("Street"));
    }


    @Test
    public void checkFindParkingById(){
        Parking parking = new Parking();
        parking.setSlotsNumber(2);
        Slot slot = new Slot();
        slot.setReserved(true);
        Slot slot1 = new Slot();
        slot1.setReserved(false);
        List<Slot> slots = new ArrayList<>();
        slots.add(slot);
        slots.add(slot1);
        parking.setSlots(slots);
        parking.setId((long) 1);
        Address address = new Address();
        address.setId((long) 1);
        address.setBuilding("build");
        address.setStreet("Street");
        address.setCity("City");
        parking.setAddress(address);
        parking.setParkingName("parking1");
        parking.setTariff(25);
        User user = new User();
        parking.setOwner(user);
        parking.setActive(true);
        ParkingDAO parkingDAO = mock(ParkingDAO.class);
        when(parkingDAO.findElementById(anyLong())).thenReturn(parking);
        ParkingService parkingService = new ParkingService(parkingDAO);
        Assert.assertTrue(parkingService.findParkingById(1).getParkingName().equals(parking.getParkingName()));
        Assert.assertTrue(parkingService.findParkingById(1).getFullness().equals("1/2"));
    }

    @Test
    public void checkFindAllParking(){
        Parking parking = new Parking();
        parking.setSlotsNumber(2);
        Slot slot = new Slot();
        slot.setReserved(true);
        Slot slot1 = new Slot();
        slot1.setReserved(false);
        List<Slot> slots = new ArrayList<>();
        slots.add(slot);
        slots.add(slot1);
        parking.setSlots(slots);
        parking.setId((long) 1);
        Address address = new Address();
        address.setId((long) 1);
        address.setBuilding("build");
        address.setStreet("Street");
        address.setCity("City");
        parking.setAddress(address);
        parking.setParkingName("parking1");
        parking.setTariff(25);
        User user = new User();
        parking.setOwner(user);
        parking.setActive(true);
        ParkingDAO parkingDAO = mock(ParkingDAO.class);
        List<Parking> parkings = new ArrayList<>();
        parkings.add(parking);
        when(parkingDAO.findAll()).thenReturn(parkings);
        ParkingService parkingService = new ParkingService(parkingDAO);
        Assert.assertTrue(parkingService.findAllParking().get(0).getParkingName().equals(parking.getParkingName()));
        Assert.assertTrue(parkingService.findAllParking().get(0).getFullness().equals("1/2"));

    }

    @Test
    public void checkFindFullness(){
        Parking parking = new Parking();
        parking.setSlotsNumber(2);
        Slot slot = new Slot();
        slot.setReserved(true);
        Slot slot1 = new Slot();
        slot1.setReserved(false);
        List<Slot> slots = new ArrayList<>();
        slots.add(slot);
        slots.add(slot1);
        parking.setSlots(slots);
        ParkingDAO parkingDAO = mock(ParkingDAO.class);
        ParkingService parkingService = new ParkingService(parkingDAO);
        System.out.println(parkingService.findFullness(parking));
        Assert.assertTrue(parkingService.findFullness(parking).contains("1/2"));
    }

}