package ua.com.parkhub;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import ua.com.parkhub.persistence.entities.Slot;
import ua.com.parkhub.service.impl.BookingService;

import java.util.List;

@SpringBootApplication

public class BackendApplication {
    @Bean
    CommandLineRunner init(BookingService bookingService) {
        return args -> {
            List<Slot> list = bookingService.findAllAvailableSlots(1578907342000L, 1578936600000L);
            System.out.println(list);
        };
    }

    public static void main(String[] args) {
        SpringApplication.run(BackendApplication.class, args);
    }
}
