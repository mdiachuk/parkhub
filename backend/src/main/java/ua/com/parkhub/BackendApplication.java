package ua.com.parkhub;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import ua.com.parkhub.model.ParkingModel;
import ua.com.parkhub.service.impl.ParkingService;

@SpringBootApplication
public class BackendApplication {

    @Bean
    CommandLineRunner init(ParkingService parkingService) {
        return args -> {
            ParkingModel parking = parkingService.findParkingByIdWithSlotList(1);
            System.out.println(parking);
        };
    }

    public static void main(String[] args) {
        SpringApplication.run(BackendApplication.class, args);

    }
}
