package ua.com.parkhub.service;

import org.junit.jupiter.api.Test;
import org.junit.Assert;
import ua.com.parkhub.persistence.entities.Address;
import ua.com.parkhub.persistence.entities.Parking;
import ua.com.parkhub.persistence.impl.ParkingDAO;

import static org.mockito.Mockito.mock;

class ParkingServiceTest {

    @Test
    public void checkCorrectReturnCityFindAddress(){
/*        Address address = mock(Address.class);
        Parking parking = mock(Parking.class);
        //ParkingDAO parkingDAO = new ParkingDAO();
//        Parking parking = parkingDAO.findElementById(1);
//        Address address = new Address();
        address.setCity("Random");
//        address.setStreet("Rand");
//        address.setBuilding("fjhk");
        parking.setAddress(address);
        ParkingService parkingService = new ParkingService();
        System.out.println(parkingService.findAddress(parking));
        Assert.assertTrue(parkingService.findAddress(parking).contains("Random"));*/

    }

    @Test
    public void checkFindParkingById(){

    }

    @Test
    public void checkFindAllParking(){

    }

    @Test
    public void checkFindFullness(){

    }

}