package ua.com.parkhub.controller;

import org.springframework.web.bind.annotation.RestController;
import ua.com.parkhub.service.impl.ParkingService;
//import ua.com.parkhub.service.impl.ParkingService;

@RestController
public class HomeController {

    private final ParkingService parkingService;

    public HomeController(ParkingService parkingService) {
        this.parkingService = parkingService;
    }
//
//    @GetMapping("/home")
//    public ResponseEntity<List<ParkingDTO>> getParking(){
//        return ResponseEntity.ok(parkingService.findAllParkings());
//    }



}
