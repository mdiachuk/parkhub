//package ua.com.parkhub.controller;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RestController;
//import ua.com.parkhub.dto.DescribedParkingDTO;
//import ua.com.parkhub.dto.ShortParkingDTO;
//import ua.com.parkhub.mapper.ParkingMapper;
//import ua.com.parkhub.service.impl.ParkingService;
//
//import java.util.List;
//import java.util.stream.Collectors;
//
//@RestController
//public class ManagerController {
//
//    private ParkingService parkingService;
//    private ParkingMapper parkingMapper;
//
//    @Autowired
//    public ManagerController(ParkingService parkingService, ParkingMapper parkingMapper) {
//        this.parkingService = parkingService;
//        this.parkingMapper = parkingMapper;
//    }
//
//    @GetMapping(value = "/manager/cabinet")
//    public ResponseEntity<List<ShortParkingDTO>> getAllParkings() {
//
//        return ResponseEntity.ok(parkingService.findAll().stream().map(parkingMapper::fromModelToShortDto).collect(Collectors.toList()));
//    }
//
//    @GetMapping(value = "/manager/cabinet/{parkingId}")
//    public ResponseEntity<DescribedParkingDTO> getParkingById(@PathVariable("parkingId") String parkingId){
//
//        return ResponseEntity.ok(parkingMapper.fromModelToDescribedDto(parkingService.findParkingByIdYaroslav(Long.parseLong(parkingId))));
//    }
//}
//
