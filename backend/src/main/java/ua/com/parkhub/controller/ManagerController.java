package ua.com.parkhub.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import ua.com.parkhub.SignUpMessage;
import ua.com.parkhub.dto.ManagerDTO;
import ua.com.parkhub.exceptions.EmailIsUsedException;
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
        SignUpMessage message = new SignUpMessage();
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
        SignUpMessage message = new SignUpMessage();
        message.setCreated(false);
        message.setDescription("Account with this phone number already exists!");
        return ResponseEntity.badRequest().body(message);
    }

    @ExceptionHandler(EmailIsUsedException.class)
    public ResponseEntity handleEmailIsUsedException() {
        SignUpMessage message = new SignUpMessage();
        message.setCreated(false);
        message.setDescription("Account with this email already exists!");
        return ResponseEntity.badRequest().body(message);
    }
}
