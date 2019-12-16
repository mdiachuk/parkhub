package ua.com.parkhub.controller;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.com.parkhub.dto.AddressDTO;
import ua.com.parkhub.dto.ParkingDTO;
import ua.com.parkhub.dto.ParkingRequestDTO;
import ua.com.parkhub.mapper.AddressMapper;
import ua.com.parkhub.mapper.ParkingMapper;
import ua.com.parkhub.mapper.ParkingRequestMapper;
import ua.com.parkhub.model.ParkingRequestModel;
import ua.com.parkhub.persistence.entities.Parking;
import ua.com.parkhub.service.ParkingService;

@RestController
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
    public ResponseEntity addParking(@RequestBody ParkingRequestDTO parkingRequestDTO) {
        // validation here
        ParkingRequestModel parkingModel = parkingRequestMapper.parkingRequestDTOToParkingRequestModel(parkingRequestDTO);
        psv.createParkingFROMParkingRequestModel(parkingModel);
        return ResponseEntity.ok().build();
    }


}
