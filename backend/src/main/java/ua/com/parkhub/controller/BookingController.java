package ua.com.parkhub.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.com.parkhub.dto.*;
import ua.com.parkhub.exceptions.BookingException;
import ua.com.parkhub.exceptions.CustomerException;
import ua.com.parkhub.service.IBookingService;

//TODO Validation: parkId, slotId, car number, phone number

@RestController
public class BookingController {
/*    private static final Logger LOGGER = LoggerFactory.getLogger(BookingController.class);

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
    //TODO implementation w.o. pagination just for small amount of slots! Will do in a next impl steps
    public ResponseEntity<ParkingWithSlotsDTO> displayParking(@PathVariable(name = "id") @Positive Long id) {
        try {
            Parking parking = parkingService.findParkingByIdWithSlotList(id);
            return ResponseEntity.ok(mapper.map(parking, ParkingWithSlotsDTO.class));
        } catch (ParkHubException e) {
            LOGGER.error(e.getMessage());
        }
        throw ParkingNotFoundException.createWith(id);
    }

    @PostMapping("/booking")
    //TODO not idempotent operation! Will do some smart restrictions on booking amount per one phone number in a next impl steps
    //TODO switch onto cyrillic after i18n impl
    public ResponseEntity<BookingDTO> addBooking(@Valid @RequestBody BookingFormDTO bookingFormDTO*//*, BindingResult result*//*) {
        //TODO to check after Angular material impl
        *//*if (result.hasErrors()) {
            List<ObjectError> errors = result.getAllErrors().stream()
                    .map(error -> new ObjectError(error.toString(), "is not appropriate"))
                    .collect(Collectors.toList());
            LOGGER.error("Validation errors: {}", errors);
            throw AddBookingException.createWith(errors);
        }*//*
        String carNumber = bookingFormDTO.getCarNumber();
        String phoneNumber = bookingFormDTO.getPhoneNumber();
        Long slotId = bookingFormDTO.getSlotId();
        Booking booking = bookingService.addBooking(carNumber, phoneNumber, slotId);
        BookingDTO bookingDTO = mapper.map(booking, BookingDTO.class);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }*/

    private IBookingService bookingService;

    @Autowired
    public BookingController(IBookingService bookingService) {
        this.bookingService = bookingService;
    }

    @RequestMapping(value = "/cancel", method = {RequestMethod.POST})
    public ResponseEntity<PaymentResponseDTO> getPhoneNumber(@RequestBody PhoneNumberDTO phoneNumber) {
        PaymentResponseDTO paymentResponseDTO = new PaymentResponseDTO();
        try {
            int price = bookingService.findPrice(phoneNumber.getPhoneNumber());
            paymentResponseDTO.setPrice(price);
            paymentResponseDTO.setStatus(true);
        } catch (BookingException e) {
            paymentResponseDTO.setStatus(false);
            paymentResponseDTO.setPrice(0);
        }
        return ResponseEntity.ok(paymentResponseDTO);
    }

    @ExceptionHandler(BookingException.class)
    public ResponseEntity handlePermissionException(BookingException e) {
        return ResponseEntity.badRequest().body(e.getStatusCode());
    }

    @ExceptionHandler(CustomerException.class)
    public ResponseEntity handlePermissionException(CustomerException e) {
        return ResponseEntity.badRequest().body(e.getStatusCode());
    }
}
