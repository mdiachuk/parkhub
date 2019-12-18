package ua.com.parkhub.controller;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.com.parkhub.dto.AddressDTO;
import ua.com.parkhub.dto.ParkingDTO;
import ua.com.parkhub.dto.ParkingRequestDTO;
import ua.com.parkhub.mapper.AddressMapper;
import ua.com.parkhub.mapper.ParkingMapper;
import ua.com.parkhub.mapper.ParkingRequestMapper;
import ua.com.parkhub.model.ParkingRequestModel;
import ua.com.parkhub.persistence.entities.Parking;
import ua.com.parkhub.service.ParkingService;

import javax.validation.Valid;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/home/cabinet/addParking")// TODO move to controller annotation
public class ParkingController {
    private ParkingService psv;
    private final AddressMapper addressMapper;
    private final ParkingMapper parkingMapper;
    private final ParkingRequestMapper parkingRequestMapper;

    @Autowired
    public ParkingController(ParkingService psv) {

        this.psv = psv;
        addressMapper = Mappers.getMapper( AddressMapper.class);
        parkingMapper = Mappers.getMapper( ParkingMapper.class);
        parkingRequestMapper = Mappers.getMapper( ParkingRequestMapper.class);
    }

    @PostMapping
    public ResponseEntity addParking(@Valid@RequestBody ParkingRequestDTO parkingRequestDTO) {
        // validation
        ParkingRequestModel parkingModel = parkingRequestMapper.parkingRequestDTOToParkingRequestModel(parkingRequestDTO);
        long id = 1;
        boolean resp = psv.checkIfParkingIsUnique(parkingModel);
        if (!resp){
            return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Bad datas!");
        }

        psv.createParkingFROMParkingRequestModel(parkingModel,id);
        return ResponseEntity.ok().build();
    }





}
