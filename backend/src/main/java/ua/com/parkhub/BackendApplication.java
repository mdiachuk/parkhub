package ua.com.parkhub;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;

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
