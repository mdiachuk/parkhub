package ua.com.parkhub;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ua.com.parkhub.model.AddressModel;
import ua.com.parkhub.model.ParkingModel;
import ua.com.parkhub.model.ParkingRequestModel;
import ua.com.parkhub.persistence.entities.*;
import ua.com.parkhub.service.CustomerService;
import ua.com.parkhub.service.ManagerService;
import ua.com.parkhub.service.ParkingService;

@SpringBootApplication
public class BackendApplication {
//
//    @Autowired
//    ParkingService parkingService;


    public static void main(String[] args) {
        SpringApplication.run(BackendApplication.class, args);

    }

//    @Override
//    public void run(String... args)  {
//
//        Customer customer = new Customer();
//        customer.setPhoneNumber("568897");
//        UserRole manager = new UserRole();
//        manager.setRoleName("manager");
//        User user1 = new User();
//        user1.setFirstName("al");
//        user1.setLastName("rom");
//        user1.setRole(manager);
//        user1.setEmail("hnjf");
//        user1.setCustomer(customer);
//        user1.setPassword("12345678");
//        Address address1 = new Address();
//        address1.setBuilding("fdefefe");
//        address1.setCity("dwwf");
//        address1.setStreet("efrgtyjyuk");
/*       ParkingRequestModel parkingRequestModel = new ParkingRequestModel();
        parkingRequestModel.setBuilding("fdefefe");
        parkingRequestModel.setCity("dwwf");
        parkingRequestModel.setStreet("efrgtyjyuk");
        parkingRequestModel.setParkingName("yghj");
        parkingRequestModel.setSlotsNumber(4);
        parkingRequestModel.setTariff(23);
        parkingRequestModel.setOwnerEmail("hnjf");
        Gson gson = new Gson();
        String Json = gson.toJson(parkingRequestModel);
        System.out.println(Json);*/
 //       parkingService.createParkingFROMParkingRequestModel(parkingRequestModel);


//        AddressModel address = new AddressModel();
//        address.setBuilding("fdefefe");
//        address.setCity("dwwf");
//        address.setStreet("efrgtyjyuk");
//        ParkingModel parking = new ParkingModel();
//        Parking parking = new Parking();
//        parking.setParkingName("yghj");
//        parking.setSlotsNumber(4);
//        parking.setTariff(23);
//        parking.setOwnerEmail("hnjf");
  //      parking.setOwnerId(1L);
   //     parking.setOwner(user1);
//        parking.setAddress(address);
//        Gson gson = new Gson();
//        String Json = gson.toJson(parking);
//        System.out.println(Json);
//          parkingService.saveCustomer(customer);
//         parkingService.saveUserRole(manager);
//          parkingService.saveUser(user1);
////        parkingService.saveAdress(address1);
//        parkingService.createParking(address1,parking);
//        parkingService.createParkingFROMModels(address,parking);

    }
//}
