package ua.com.parkhub.controller;

import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.com.parkhub.dto.ParkingRequestDTO;
import ua.com.parkhub.mapper.ParkingRequestMapper;
import ua.com.parkhub.model.AddressModel;
import ua.com.parkhub.model.ParkingModel;
import ua.com.parkhub.model.ParkingRequestModel;
import ua.com.parkhub.service.ParkingService;

import javax.validation.Valid;

@RestController
//@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/home/cabinet/addParking")
public class ParkingController {

    private final ParkingService parkingService;
    private final ParkingRequestMapper parkingRequestMapper;

    @Autowired
    public ParkingController(ParkingService parkingService) {

        this.parkingService = parkingService;
        parkingRequestMapper = Mappers.getMapper( ParkingRequestMapper.class);
    }

    @PostMapping
    public ResponseEntity addParking(@Valid@RequestBody ParkingRequestDTO parkingRequestDTO) {
        ParkingRequestModel parkingRequestModel = parkingRequestMapper.parkingRequestDTOToParkingRequestModel(parkingRequestDTO);
        if (!parkingService.isParkingNameUnique(parkingRequestModel)){
            return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body("This parking name already exists");
        }

        if (!parkingService.checkIfAddressIsUnique(parkingRequestModel)){
            return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Parking with that address already exists!");
        }
        AddressModel addressModel = parkingService.createAddressModelFROMParkingRequestModel(parkingRequestModel);
        ParkingModel parkingModel = parkingService.createParkingModelFROMParkingRequestModel(parkingRequestModel);
        long id = 1;// TODO check if username is correct
        parkingService.createParkingFromParkingAndAddressModel(addressModel,parkingModel,id);
        return ResponseEntity.ok().build();
    }


//    @PostMapping
//    public ResponseEntity addParking(@Valid@RequestBody ParkingRequestDTO parkingRequestDTO,@RequestHeader("X-auth") String s) {
//        ParkingRequestModel parkingRequestModel = parkingRequestMapper.parkingRequestDTOToParkingRequestModel(parkingRequestDTO);
//        if (!parkingService.isParkingNameUnique(parkingRequestModel)){
//            return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body("This parking name already exists");
//        }
//
//        if (!parkingService.checkIfAddressIsUnique(parkingRequestModel)){
//            return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Parking with that address already exists!");
//        }
//        JwtUtil j = new JwtUtil();
//        Claims c = j.getClaims(s);
//        Long i = c.get("id", Long.class);
//        AddressModel addressModel = parkingService.createAddressModelFROMParkingRequestModel(parkingRequestModel);
//        ParkingModel parkingModel = parkingService.createParkingModelFROMParkingRequestModel(parkingRequestModel);
//        long id = 1;// TODO check if username is correct
//        parkingService.createParkingFromParkingAndAddressModel(addressModel,parkingModel,id);
//        return ResponseEntity.ok().build();
//    }





}
