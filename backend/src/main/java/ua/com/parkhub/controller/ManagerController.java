package ua.com.parkhub.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.com.parkhub.dto.ParkingDTO;
import ua.com.parkhub.mappers.ParkingMapper;
import ua.com.parkhub.service.ParkingService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class ManagerController {

    private ParkingService parkingService;
    private ParkingMapper parkingMapper;

    @Autowired
    public ManagerController(ParkingService parkingService, ParkingMapper parkingMapper) {
        this.parkingService = parkingService;
        this.parkingMapper = parkingMapper;
    }

    @GetMapping(value = "/parkings")
    public ResponseEntity<List<ParkingDTO>> getAllParkings() {
        return ResponseEntity.ok(parkingService.findAll().stream().map(parkingMapper::fromModelToDto).collect(Collectors.toList()));
//        List<ParkingDTO> testlist= new ArrayList<>();
//        testlist.add(new ParkingDTO("Parkovka", "Kyiv"));
//        return ResponseEntity.ok(testlist);
    }
}
