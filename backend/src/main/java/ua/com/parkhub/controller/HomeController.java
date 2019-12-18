package ua.com.parkhub.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.com.parkhub.dto.ParkingDTO;
import ua.com.parkhub.persistence.entities.Address;
import ua.com.parkhub.persistence.entities.Parking;
import ua.com.parkhub.persistence.entities.Slot;
import ua.com.parkhub.persistence.entities.User;
import ua.com.parkhub.persistence.impl.ParkingDAO;
import ua.com.parkhub.service.ParkingService;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

@RestController
public class HomeController {

    private final ParkingService parkingService;

    public HomeController(ParkingService parkingService) {
        this.parkingService = parkingService;
    }

    @GetMapping("/home")
    public ResponseEntity<List<ParkingDTO>> getParking(){
        return ResponseEntity.ok(parkingService.findAllParking());
    }



}
