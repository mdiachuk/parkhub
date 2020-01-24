package ua.com.parkhub.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ua.com.parkhub.dto.ParkingDTO;
import ua.com.parkhub.dto.ParkingUpdateRequestDTO;
import ua.com.parkhub.mappers.Mapper;
import ua.com.parkhub.model.ParkingModel;
import ua.com.parkhub.service.IParkingService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/manager/cabinet")
public class ManagerController {

    private IParkingService parkingService;
    private Mapper<ParkingModel, ParkingDTO> parkingMapper;
    private Mapper<ParkingUpdateRequestDTO, ParkingModel> parkingRequestDtoToModelMapper;

    @Autowired
    public ManagerController(IParkingService parkingService, Mapper<ParkingModel, ParkingDTO> parkingModelToDTOMapper,  Mapper<ParkingUpdateRequestDTO, ParkingModel> parkingRequestDtoToModelMapper) {
        this.parkingService = parkingService;
        this.parkingMapper = parkingModelToDTOMapper;
        this.parkingRequestDtoToModelMapper = parkingRequestDtoToModelMapper;
    }

    @PreAuthorize("hasRole('MANAGER') ")
    @GetMapping(value = "/all/{ownerId}")
    public ResponseEntity<List<ParkingDTO>> getAllParkingByOwnerId(@PathVariable("ownerId") Long parkingId) {

        return ResponseEntity.ok(parkingService.findAllParkingByOwnerId(parkingId).stream().map(parkingMapper::transform).collect(Collectors.toList()));
    }

    @PreAuthorize("hasRole('MANAGER') ")
    @GetMapping(value = "/{parkingId}")
    public ResponseEntity<ParkingDTO> getParkingById(@PathVariable("parkingId") Long parkingId){

        return ResponseEntity.ok(parkingMapper.transform(parkingService.findParkingById(parkingId)));
    }

    @PreAuthorize("hasRole('MANAGER') ")
    @PutMapping(value = "/{parkingId}")
    public ResponseEntity<Void> updateParking(@PathVariable("parkingId") Long parkingId, @RequestBody ParkingUpdateRequestDTO requestDTO) {
        parkingService.updateParking(parkingId, parkingRequestDtoToModelMapper.transform(requestDTO));
        return ResponseEntity.ok().build();
    }
}

