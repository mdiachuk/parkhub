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
    public static void main(String[] args) {
        SpringApplication.run(BackendApplication.class, args);
    }
}
