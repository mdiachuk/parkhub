package ua.com.parkhub.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.com.parkhub.dto.BookingDTO;
import ua.com.parkhub.dto.BookingFormDTO;
import ua.com.parkhub.dto.ParkingWithSlotsDTO;
import ua.com.parkhub.model.Booking;
import ua.com.parkhub.model.Parking;
import ua.com.parkhub.service.IBookingService;
import ua.com.parkhub.service.IParkingService;

import javax.validation.Valid;
import javax.validation.constraints.Positive;

//TODO Validation: parkId, slotId, car number, phone number

@RestController
public class BookingController {

    private final IParkingService parkingService;
    private final IBookingService bookingService;
    private final ModelMapper mapper;

    @Autowired
    public BookingController(IParkingService parkingService, IBookingService bookingService, ModelMapper mapper) {
        this.parkingService = parkingService;
        this.bookingService = bookingService;
        this.mapper = mapper;
    }

    @GetMapping("/parkings/{id}")
    public ParkingWithSlotsDTO displayParking(@PathVariable @Positive int id) {
        System.out.println("CONTROLLER: @GetMapping(api/parkings/{id})");
        //TODO try-catch
        Parking parking = parkingService.findParkingByIdWithSlotList(id);
        System.out.println(parking);
        ParkingWithSlotsDTO parkingWithSlotsDTO = mapper.map(parking, ParkingWithSlotsDTO.class);
        System.out.println(parkingWithSlotsDTO);
        return parkingWithSlotsDTO;


    }

    @PostMapping("/booking")
    //TODO how to validate @RequestBody: notNull, "", pattern matches
    //TODO switch onto cyrillic after i18n impl
    public ResponseEntity<BookingDTO> addBooking(@Valid @RequestBody BookingFormDTO bookingFormDTO) {
        System.out.println("CONTROLLER: @PostMapping(/api/booking)");
        String carNumber = bookingFormDTO.getCarNumber();
        System.out.println("carNumber: " + carNumber);
        String phoneNumber = bookingFormDTO.getPhoneNumber();
        System.out.println("phoneNumber: "+ phoneNumber);
        Long slotId = bookingFormDTO.getSlotId();
        Booking booking = bookingService.addBooking(carNumber, phoneNumber, slotId);
        //TODO try-catch
        BookingDTO bookingDTO = mapper.map(booking, BookingDTO.class);
        /*return new ResponseEntity<>(bookingDTO, HttpStatus.OK);*/
        /*return ResponseEntity.ok().build();*/
        return ResponseEntity.ok(bookingDTO);
    }
}
