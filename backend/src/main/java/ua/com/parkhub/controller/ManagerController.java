package ua.com.parkhub.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.com.parkhub.dto.ParkingDTO;
import ua.com.parkhub.dto.ParkingRequestDTO;
import ua.com.parkhub.mappers.fromDtoToModel.ParkingRequestDtoToModelMapper;
import ua.com.parkhub.mappers.fromModelToDTO.ParkingModelToDTOMapper;
import ua.com.parkhub.service.impl.ParkingService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/parkings")
public class ManagerController {

    private ParkingService parkingService;
    private ParkingModelToDTOMapper parkingMapper;
    private ParkingRequestDtoToModelMapper parkingRequestDtoToModelMapper;

    @Autowired
    public ManagerController(ParkingService parkingService, ParkingModelToDTOMapper parkingModelToDTOMapper, ParkingRequestDtoToModelMapper parkingRequestDtoToModelMapper) {
        this.parkingService = parkingService;
        this.parkingMapper = parkingModelToDTOMapper;
        this.parkingRequestDtoToModelMapper = parkingRequestDtoToModelMapper;
    }

    @GetMapping()
    public ResponseEntity<List<ParkingDTO>> getAllParking() {

        return ResponseEntity.ok(parkingService.findAllParking().stream().map(parkingMapper::transform).collect(Collectors.toList()));
    }

    @GetMapping(value = "/{parkingId}")
    public ResponseEntity<ParkingDTO> getParkingById(@PathVariable("parkingId") Long parkingId){

        return ResponseEntity.ok(parkingMapper.transform(parkingService.findParkingById(parkingId).get()));
    }

    @PutMapping(value = "/{parkingId}")
    public /*ResponseEntity<Void>*/ String updateParking(@PathVariable("parkingId") Long parkingId, @RequestBody ParkingRequestDTO requestDTO) throws NoSuchFieldException, IllegalAccessException {
        /*parkingService.updateParking(parkingId, parkingRequestDtoToModelMapper.transform(requestDTO));
        return ResponseEntity.ok().build();*/
        return "ZDAROVA";
    }
}

