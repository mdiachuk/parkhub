package ua.com.parkhub.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.com.parkhub.dto.ParkingDTO;
import ua.com.parkhub.service.ParkingService;

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
