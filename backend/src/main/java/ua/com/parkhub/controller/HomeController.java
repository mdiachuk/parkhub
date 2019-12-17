package ua.com.parkhub.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.com.parkhub.dto.ParkingDTO;
import ua.com.parkhub.service.ParkingService;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class HomeController {

    @Autowired
    private ParkingService parkingService;

    @GetMapping("/home")
    public ResponseEntity<List<ParkingDTO>> getParking(){
        return ResponseEntity.ok(parkingService.findAllParking());
        //parkingService.findAllParking();
    }



}
