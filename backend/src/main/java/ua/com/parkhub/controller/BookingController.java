package ua.com.parkhub.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.com.parkhub.dto.BookingDTO;
import ua.com.parkhub.dto.BookingFormDTO;
import ua.com.parkhub.dto.ParkingWithSlotsDTO;
import ua.com.parkhub.exceptions.ParkHubException;
import ua.com.parkhub.exceptions.ParkingNotFoundException;
import ua.com.parkhub.mappers.modelToDto.BookingModelToDTOMapper;
import ua.com.parkhub.mappers.modelToDto.ParkingWithSlotsModelToDTOMapper;
import ua.com.parkhub.model.BookingModel;
import ua.com.parkhub.model.ParkingModel;
import ua.com.parkhub.service.IBookingService;
import ua.com.parkhub.service.IParkingService;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.Positive;

//TODO Validation: parkId, slotId, car number, phone number

@RestController
public class BookingController {
    private static final Logger LOGGER = LoggerFactory.getLogger(BookingController.class);

    private final IParkingService parkingService;
    private final IBookingService bookingService;
    private final ParkingWithSlotsModelToDTOMapper parkingWithSlotsModelToDTOMapper;
    private final BookingModelToDTOMapper bookingModelToDTOMapper;

    @Autowired
    public BookingController(IParkingService parkingService, IBookingService bookingService, ParkingWithSlotsModelToDTOMapper parkingWithSlotsModelToDTOMapper,
                             BookingModelToDTOMapper bookingModelToDTOMapper) {
        this.parkingService = parkingService;
        this.bookingService = bookingService;
        this.parkingWithSlotsModelToDTOMapper = parkingWithSlotsModelToDTOMapper;
        this.bookingModelToDTOMapper = bookingModelToDTOMapper;
    }

    @GetMapping("/parkings/{id}")
    //TODO implementation w.o. pagination just for small amount of slots! Will do in a next impl steps
    public ResponseEntity<ParkingWithSlotsDTO> displayParking(@PathVariable(name = "id") @Positive Long id, @RequestParam @Min(value = 0) String rangeFrom,
                                                              @RequestParam @Min(value = 0) String rangeTo) {
        try {
            Long checkIn = Long.valueOf(rangeFrom);
            Long checkOut = Long.valueOf(rangeTo);
            ParkingModel parking = parkingService.findParkingByIdWithSlotListAndDateRange(id, checkIn, checkOut);
            return ResponseEntity.ok(parkingWithSlotsModelToDTOMapper.transform(parking));
        } catch (ParkHubException e) {
            LOGGER.error(e.getMessage());
        }
        throw ParkingNotFoundException.createWith(id);
    }

    /*@GetMapping("/parkings/{id}")
    //TODO implementation w.o. pagination just for small amount of slots! Will do in a next impl steps
    public ResponseEntity<ParkingWithSlotsDTO> displayParking(@PathVariable(name = "id") @Positive Long id) {
        try {
            ParkingModel parking = parkingService.findParkingByIdWithSlotList(id);
            return ResponseEntity.ok(parkingWithSlotsModelToDTOMapper.transform(parking));
        } catch (ParkHubException e) {
            LOGGER.error(e.getMessage());
        }
        throw ParkingNotFoundException.createWith(id);
    }*/

    @PostMapping("/booking")
    //TODO not idempotent operation! Will do some smart restrictions on booking amount per one phone number in a next impl steps
    //TODO switch onto cyrillic after i18n impl
    public ResponseEntity<BookingDTO> addBooking(@Valid @RequestBody BookingFormDTO bookingFormDTO/*, BindingResult result*/) {
        //TODO to check after Angular material impl
        /*if (result.hasErrors()) {
            List<ObjectError> errors = result.getAllErrors().stream()
                    .map(error -> new ObjectError(error.toString(), "is not appropriate"))
                    .collect(Collectors.toList());
            LOGGER.error("Validation errors: {}", errors);
            throw AddBookingException.createWith(errors);
        }*/
        String carNumber = bookingFormDTO.getCarNumber();
        String phoneNumber = bookingFormDTO.getPhoneNumber();
        Long slotId = bookingFormDTO.getSlotId();
        Long checkIn = bookingFormDTO.getRangeFrom();
        System.out.println(checkIn);
        Long checkOut = bookingFormDTO.getRangeTo();
        System.out.println(checkOut);
        BookingModel booking = bookingService.addBooking(carNumber, phoneNumber, slotId, checkIn, checkOut);
        return ResponseEntity.ok(bookingModelToDTOMapper.transform(booking));
    }
}
