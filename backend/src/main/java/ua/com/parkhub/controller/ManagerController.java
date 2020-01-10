
package ua.com.parkhub.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ua.com.parkhub.dto.ParkingDTO;
import ua.com.parkhub.mappers.modelToDto.ParkingModelToDTOMapper;
import ua.com.parkhub.service.impl.ParkingService;

import java.util.List;

@RestController
public class ManagerController {

    private ParkingService parkingService;
    private ParkingModelToDTOMapper parkingMapper;

    @Autowired
    public ManagerController(ParkingService parkingService, ParkingModelToDTOMapper parkingModelToDTOMapper) {
        this.parkingService = parkingService;
        this.parkingMapper = parkingModelToDTOMapper;
    }

    @GetMapping(value = "/manager/cabinet")
    public ResponseEntity<List<ParkingDTO>> getAllParking() {
return null;
//        return ResponseEntity.ok(parkingService.findAllParking().stream().map(parkingMapper::transform).collect(Collectors.toList()));
    }

    @GetMapping(value = "/manager/cabinet/{parkingId}")
    public ResponseEntity<ParkingDTO> getParkingById(@PathVariable("parkingId") String parkingId){

        return ResponseEntity.ok(parkingMapper.transform(parkingService.findParkingById(Long.parseLong(parkingId)).get()));
    }

}
