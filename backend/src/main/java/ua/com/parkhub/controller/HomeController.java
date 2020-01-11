package ua.com.parkhub.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.com.parkhub.dto.ParkingDTO;
import ua.com.parkhub.service.IParkingService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class HomeController {

    private IParkingService parkingService;
    private ParkingModelToDTOMapper parkingMapper;

    @Autowired
    public HomeController(IParkingService parkingService, ParkingModelToDTOMapper parkingModelToDTOMapper) {
        this.parkingService = parkingService;
        this.parkingMapper = parkingModelToDTOMapper;
    }

    @GetMapping("/api/home")
    public ResponseEntity<List<ParkingDTO>> getAllParking() {
        return ResponseEntity.ok(parkingService.findAllParking().stream().map(parkingMapper::transform).collect(Collectors.toList()));
        //return ResponseEntity.ok(parkingMapper.transform(parkingService.findAllParking()))

    }
}
