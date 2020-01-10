package ua.com.parkhub.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.com.parkhub.dto.BookingFormDTO;
import ua.com.parkhub.dto.ParkingWithSlotsDTO;
import ua.com.parkhub.dto.PaymentDTO;
import ua.com.parkhub.exceptions.ParkHubException;
import ua.com.parkhub.exceptions.ParkingException;
import ua.com.parkhub.mappers.modelToDto.ParkingWithSlotsModelToDTOMapper;
import ua.com.parkhub.mappers.modelToDto.PaymentModelToDTOMapper;
import ua.com.parkhub.model.ParkingModel;
import ua.com.parkhub.model.PaymentModel;
import ua.com.parkhub.service.IBookingService;
import ua.com.parkhub.service.IParkingService;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.Positive;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

//TODO Validation: parkId, slotId, car number, phone number

@RestController
public class BookingController {
    private static final Logger LOGGER = LoggerFactory.getLogger(BookingController.class);

    private final IParkingService parkingService;
    private final IBookingService bookingService;
    private final ParkingWithSlotsModelToDTOMapper parkingWithSlotsModelToDTOMapper;
    private final PaymentModelToDTOMapper paymentModelToDTOMapper;
    //TODO @Min annotation add for rangeFrom and rangeTo validation
    public static final long NOW = LocalDateTime.now().toEpochSecond(ZoneOffset.UTC);
    public static final long NOW_PLUS_HOUR = LocalDateTime.now().plusHours(1).toEpochSecond(ZoneOffset.UTC);

    @Autowired
    public BookingController(IParkingService parkingService, IBookingService bookingService, ParkingWithSlotsModelToDTOMapper parkingWithSlotsModelToDTOMapper,
                             PaymentModelToDTOMapper paymentModelToDTOMapper) {
        this.parkingService = parkingService;
        this.bookingService = bookingService;
        this.parkingWithSlotsModelToDTOMapper = parkingWithSlotsModelToDTOMapper;
        this.paymentModelToDTOMapper = paymentModelToDTOMapper;
    }

    @GetMapping("/parkings/{id}")
    //TODO implementation w.o. pagination just for small amount of slots! Will do in a next impl steps
    public ResponseEntity<ParkingWithSlotsDTO> displayParking(@PathVariable(name = "id") @Positive @Min(1) Long id, @RequestParam @Positive Long rangeFrom,
                                                              @RequestParam @Positive Long rangeTo) {
        try {
            ParkingModel parking = parkingService.findParkingByIdWithSlotListAndDateRange(id, rangeFrom, rangeTo);
            LOGGER.info("Request displayParking() successfully completed");
            return ResponseEntity.ok(parkingWithSlotsModelToDTOMapper.transform(parking));
        } catch (ParkHubException e) {
            LOGGER.error(e.getMessage());
        }
        throw ParkingException.createWith(id);
    }

    @PostMapping("/booking")
    //TODO not idempotent operation! Will do some smart restrictions on booking amount per one phone number in a next impl steps
    //TODO switch onto cyrillic after i18n impl
    public ResponseEntity<PaymentDTO> addBooking(@Valid @RequestBody BookingFormDTO bookingFormDTO/*, BindingResult result*/) {
        LOGGER.debug(bookingFormDTO.toString());
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
        Long checkOut = bookingFormDTO.getRangeTo();
        Integer tariff = bookingFormDTO.getTariff();
        PaymentModel payment = bookingService.addBooking(carNumber, phoneNumber, slotId, checkIn, checkOut, tariff);
        LOGGER.info("Request addBooking() successfully completed");
        return ResponseEntity.ok(paymentModelToDTOMapper.transform(payment));
    }
}
