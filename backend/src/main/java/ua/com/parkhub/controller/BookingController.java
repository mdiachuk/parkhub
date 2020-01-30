package ua.com.parkhub.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.com.parkhub.dto.*;
import ua.com.parkhub.exceptions.ParkHubException;
import ua.com.parkhub.mappers.modelToDto.ParkingWithSlotsModelToDTOMapper;
import ua.com.parkhub.mappers.modelToDto.PaymentModelToDTOMapper;
import ua.com.parkhub.model.ParkingModel;
import ua.com.parkhub.model.PaymentModel;
import ua.com.parkhub.service.IBookingService;
import ua.com.parkhub.service.IParkingService;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.Positive;
import java.util.logging.Logger;

@RestController
public class BookingController {
    private static final Logger LOGGER = Logger.getLogger(BookingController.class.getName());

    private final IParkingService parkingService;
    private final IBookingService bookingService;
    private final ParkingWithSlotsModelToDTOMapper parkingWithSlotsModelToDTOMapper;
    private final PaymentModelToDTOMapper paymentModelToDTOMapper;

    @Autowired
    public BookingController(IParkingService parkingService, IBookingService bookingService, ParkingWithSlotsModelToDTOMapper parkingWithSlotsModelToDTOMapper,
                             PaymentModelToDTOMapper paymentModelToDTOMapper) {
        this.parkingService = parkingService;
        this.bookingService = bookingService;
        this.parkingWithSlotsModelToDTOMapper = parkingWithSlotsModelToDTOMapper;
        this.paymentModelToDTOMapper = paymentModelToDTOMapper;
    }

    @GetMapping("/api/parkings/{id}")
    public ResponseEntity<ParkingWithSlotsDTO> displayParking(@PathVariable(name = "id") @Positive @Min(1) Long id, @RequestParam @Positive Long rangeFrom,
                                                              @RequestParam @Positive Long rangeTo) {
        if (rangeFrom < rangeTo) {
            try {
                ParkingModel parking = parkingService.findParkingByIdWithSlotListAndDateRange(id, rangeFrom, rangeTo);
                LOGGER.info("Request displayParking() successfully completed");
                return ResponseEntity.ok(parkingWithSlotsModelToDTOMapper.transform(parking));
            } catch (ParkHubException e) {
                LOGGER.warning(e.getMessage());
            }
        }
        throw new ParkHubException("check In is greater than check Out");
    }

    @PostMapping("/api/booking")
    public ResponseEntity<PaymentDTO> addBooking(@Valid @RequestBody BookingFormDTO bookingFormDTO) {
        String carNumber = bookingFormDTO.getCarNumber();
        String phoneNumber = bookingFormDTO.getPhoneNumber();
        Long slotId = bookingFormDTO.getSlotId();
        Long checkIn = bookingFormDTO.getRangeFrom();
        Long checkOut = bookingFormDTO.getRangeTo();
        if (checkIn > checkOut) {
            throw new ParkHubException("check In is greater than check Out");
        }
        Integer tariff = bookingFormDTO.getTariff();
        PaymentModel payment = bookingService.addBooking(carNumber, phoneNumber, slotId, checkIn, checkOut, tariff);
        LOGGER.info("Request addBooking() successfully completed");
        return ResponseEntity.ok(paymentModelToDTOMapper.transform(payment));
    }
    @PostMapping(value = "/api/cancel")
    public ResponseEntity<PaymentResponseDTO> cancelPrepayment(@RequestBody PhoneNumberDTO phoneNumber) {
        PaymentResponseDTO paymentResponseDTO = new PaymentResponseDTO();
        int price = bookingService.findPrice(phoneNumber.getPhoneNumber());
        paymentResponseDTO.setPrice(price);
        return ResponseEntity.ok(paymentResponseDTO);
    }
}
