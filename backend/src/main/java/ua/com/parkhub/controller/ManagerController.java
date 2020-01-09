package ua.com.parkhub.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.com.parkhub.dto.ParkingDTO;
import ua.com.parkhub.dto.ParkingUpdateRequestDTO;
import ua.com.parkhub.exceptions.ParkingDoesntExistException;
import ua.com.parkhub.exceptions.StatusCode;
import ua.com.parkhub.mappers.fromDtoToModel.ParkingRequestDtoToModelMapper;
import ua.com.parkhub.mappers.fromModelToDTO.ParkingModelToDTOMapper;
import ua.com.parkhub.service.ParkingService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/manager/cabinet")
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

        return ResponseEntity.ok(parkingMapper.transform(parkingService.findParkingById(parkingId)));
    }

    @PutMapping(value = "/{parkingId}")
    public ResponseEntity<Void> updateParking(@PathVariable("parkingId") Long parkingId, @RequestBody ParkingUpdateRequestDTO requestDTO) {
        parkingService.updateParking(parkingId, parkingRequestDtoToModelMapper.transform(requestDTO));
        return ResponseEntity.ok().build();
    }

    @ExceptionHandler(ParkingDoesntExistException.class)
    public ResponseEntity<Integer> handleEmailException(ParkingDoesntExistException e) {
        return ResponseEntity.badRequest().body(e.getStatusCode().getCode());
    }
}

