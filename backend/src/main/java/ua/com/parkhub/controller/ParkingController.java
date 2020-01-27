package ua.com.parkhub.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ua.com.parkhub.dto.ParkingRequestDTO;
import ua.com.parkhub.mappers.Mapper;
import ua.com.parkhub.model.ParkingModel;
import ua.com.parkhub.service.IParkingService;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/manager/parking")
public class ParkingController {

    private final IParkingService parkingService;
    private final Mapper<ParkingRequestDTO, ParkingModel> parkingRequestDTOWithIDtoParkingModel;

    @Autowired
    public ParkingController(IParkingService parkingService,
                             Mapper<ParkingRequestDTO, ParkingModel> parkingRequestDTOWithIDtoParkingModel) {
        this.parkingService = parkingService;
        this.parkingRequestDTOWithIDtoParkingModel = parkingRequestDTOWithIDtoParkingModel;
    }

    @PreAuthorize("MANAGER")
    @PostMapping
    public ResponseEntity addParking(@Valid @RequestBody ParkingRequestDTO parkingRequestDTO, BindingResult result) {
        if (result.hasErrors()) {
            List<String> errors = result.getAllErrors().stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .collect(Collectors.toList());
            return new ResponseEntity(errors, HttpStatus.BAD_REQUEST);
        }
        ParkingModel parkingModel = parkingRequestDTOWithIDtoParkingModel.transform(parkingRequestDTO);
        parkingService.createParkingByOwnerID(parkingModel,parkingRequestDTO.getId());
        return ResponseEntity.ok().build();
    }


}
