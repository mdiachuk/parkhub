package ua.com.parkhub.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import ua.com.parkhub.controller.messages.ResponseMessage;
import ua.com.parkhub.dto.ManagerDTO;
import ua.com.parkhub.exceptions.EmailIsUsedException;
import ua.com.parkhub.exceptions.NotFoundInDataBaseException;
import ua.com.parkhub.exceptions.PhoneNumberIsUsedException;
import ua.com.parkhub.service.SignUpService;

import javax.validation.Valid;

@RestController
@RequestMapping("/manager")
public class ManagerController {

    private final SignUpService signUpService;

    @Autowired
    public ManagerController(SignUpService signUpService) {
        this.signUpService = signUpService;
    }

    @PostMapping(value = "/signup")
    public ResponseEntity registerManager(@RequestBody @Valid ManagerDTO managerDto, BindingResult result) {
        ResponseMessage message = new ResponseMessage();
        if (result.hasErrors()) {
            message.setCreated(false);
            message.setDescription("Invalid input data.");
            return ResponseEntity.badRequest().body(message);
        }
        signUpService.registerManager(managerDto);
        message.setCreated(true);
        message.setDescription("New manager created.");
        return ResponseEntity.ok(message);
    }

    @ExceptionHandler(PhoneNumberIsUsedException.class)
    public ResponseEntity handlePhoneNumberIsUsedException() {
        ResponseMessage message = new ResponseMessage();
        message.setCreated(false);
        message.setDescription("Account with this phone number already exists!");
        return ResponseEntity.badRequest().body(message);
    }

    @ExceptionHandler(EmailIsUsedException.class)
    public ResponseEntity handleEmailIsUsedException() {
        ResponseMessage message = new ResponseMessage();
        message.setCreated(false);
        message.setDescription("Account with this email already exists!");
        return ResponseEntity.badRequest().body(message);
    }

    @ExceptionHandler(NotFoundInDataBaseException.class)
    public ResponseEntity handleNotFoundInDataBaseException() {
        ResponseMessage message = new ResponseMessage();
        message.setCreated(false);
        message.setDescription("Something went wrong on our server. Please, try again later.");
        return ResponseEntity.status(500).body(message);
    }
}
