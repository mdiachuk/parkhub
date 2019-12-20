package ua.com.parkhub;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ua.com.parkhub.dto.ParkingDTO;
import ua.com.parkhub.mappers.ParkingMapper;
import ua.com.parkhub.model.AddressModel;
import ua.com.parkhub.model.ParkingModel;
import ua.com.parkhub.persistence.entities.*;
import ua.com.parkhub.service.ParkingService;

@SpringBootApplication
public class BackendApplication  {



    public static void main(String[] args) {
        SpringApplication.run(BackendApplication.class, args);
    }


}
