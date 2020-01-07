package ua.com.parkhub.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ua.com.parkhub.dto.PaymentResponseDTO;
import ua.com.parkhub.dto.PhoneNumberDTO;
import ua.com.parkhub.exceptions.NullCustomerException;
import ua.com.parkhub.service.impl.PaymentService;


@RestController
public class PaymentController {
}
