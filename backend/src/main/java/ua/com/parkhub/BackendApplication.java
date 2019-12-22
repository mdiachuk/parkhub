package ua.com.parkhub;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import ua.com.parkhub.model.Customer;
import ua.com.parkhub.model.Parking;
import ua.com.parkhub.persistence.entities.ParkingEntity;
import ua.com.parkhub.service.impl.CustomerService;
import ua.com.parkhub.service.impl.ParkingService;

import java.util.List;

@SpringBootApplication
public class BackendApplication {

   /* @Bean
    CommandLineRunner init(ParkingService parkingService) {
        return args -> {
            Parking parking = parkingService.findParkingByIdWithSlotList(1);
            System.out.println(parking);
        };
    }*/

    public static void main(String[] args) {
        SpringApplication.run(BackendApplication.class, args);
    }
}
