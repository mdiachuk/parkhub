package ua.com.parkhub.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.com.parkhub.dto.PaymentResponseDTO;
import ua.com.parkhub.dto.PhoneNumberDTO;
import ua.com.parkhub.exceptions.NullCustomerException;
import ua.com.parkhub.service.PaymentService;

@RestController
public class PaymentController {

    private PaymentService paymentService;

    @Autowired
    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @RequestMapping(value = "api/parkoff", method = {RequestMethod.GET, RequestMethod.POST})
    public ResponseEntity<PaymentResponseDTO> getPhoneNumber(@RequestBody PhoneNumberDTO phoneNumber) {

        PaymentResponseDTO paymentResponseDTO = new PaymentResponseDTO();
        try {
            int price = paymentService.getPrice("+" + phoneNumber.getPhoneNumber());
            paymentResponseDTO.setPrice(price);
            paymentResponseDTO.setStatus(true);
        } catch (NullCustomerException e) {
            paymentResponseDTO.setStatus(false);
            paymentResponseDTO.setPrice(0);
        }
        return ResponseEntity.ok(paymentResponseDTO);
    }
}
