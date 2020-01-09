package ua.com.parkhub;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import ua.com.parkhub.model.BookingModel;
import ua.com.parkhub.model.ParkingModel;
import ua.com.parkhub.service.impl.BookingService;
import ua.com.parkhub.service.impl.ParkingService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@SpringBootApplication
public class BackendApplication {
    long checkIn = 1582050600000L;
    long checkOut = 1582140600000L;

    @Bean
    CommandLineRunner init(ParkingService parkingService) {
        return args -> {
            ParkingModel parkingModel = parkingService.findParkingByIdWithSlotListAndDateRange(1L, checkIn, checkOut);
            System.out.println(parkingModel);
        };
    }

    public static void main(String[] args) {
        SpringApplication.run(BackendApplication.class, args);

    }
}
