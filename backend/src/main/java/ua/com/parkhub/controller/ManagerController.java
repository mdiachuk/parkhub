package ua.com.parkhub.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.com.parkhub.dto.ParkingDTO;
import ua.com.parkhub.dto.ParkingRequestDTO;
import ua.com.parkhub.mappers.fromModelToDTO.ParkingModelToDTOMapper;
import ua.com.parkhub.service.ParkingService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/parkings")
public class ManagerController {

    private ParkingService parkingService;
    private ParkingModelToDTOMapper parkingMapper;

    @Autowired
    public ManagerController(ParkingService parkingService, ParkingModelToDTOMapper parkingModelToDTOMapper) {
        this.parkingService = parkingService;
        this.parkingMapper = parkingModelToDTOMapper;
    }

    @GetMapping()
    public ResponseEntity<List<ParkingDTO>> getAllParking() {

        return ResponseEntity.ok(parkingService.findAllParking().stream().map(parkingMapper::transform).collect(Collectors.toList()));
    }

    @GetMapping(value = "/{parkingId}")
    public ResponseEntity<ParkingDTO> getParkingById(@PathVariable("parkingId") String parkingId){

        return ResponseEntity.ok(parkingMapper.transform(parkingService.findParkingById(Long.parseLong(parkingId)).get()));
    }

    @PutMapping(value = "/{parkingId}")
    public String updateParking(@PathVariable("parkingId") String parkingId, @RequestBody ParkingRequestDTO requestDTO){

        return "ZDAROVA";
    }
}

