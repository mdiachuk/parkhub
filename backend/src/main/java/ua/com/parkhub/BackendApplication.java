package ua.com.parkhub;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

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
