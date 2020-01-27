package ua.com.parkhub.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ua.com.parkhub.dto.ParkingDTO;
import ua.com.parkhub.mappers.modelToDto.ParkingModelToDTOMapper;
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
    public ResponseEntity<List<ParkingDTO>> getAllParking(@RequestParam (defaultValue = "all") String address) {
        if (!address.equals("all")){
            List<ParkingDTO> list = parkingService.findParkingInArea(address).stream().map(parkingMapper::transform).collect(Collectors.toList());
            if (list.isEmpty()){
                ParkingDTO parkingDTO = new ParkingDTO();
                parkingDTO.setAddress("There is no parking at the designated location: "+address);
                list.add(parkingDTO);
            }
            return ResponseEntity.ok(list);
        }
            return ResponseEntity.ok(parkingService.findAllParking().stream().map(parkingMapper::transform).collect(Collectors.toList()));
    }


}
