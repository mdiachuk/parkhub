package ua.com.parkhub.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import ua.com.parkhub.dto.ManagerDTO;
import ua.com.parkhub.exceptions.EmailIsUsedException;
import ua.com.parkhub.exceptions.NotFoundInDataBaseException;
import ua.com.parkhub.exceptions.PhoneNumberIsUsedException;
import ua.com.parkhub.service.SignUpService;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

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
        if (result.hasErrors()) {
            List<String> errors = result.getAllErrors().stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .collect(Collectors.toList());
            return ResponseEntity.badRequest().body(errors);
        }
        signUpService.registerManager(managerDto);
        return ResponseEntity.ok().build();
    }

    @ExceptionHandler(PhoneNumberIsUsedException.class)
    public ResponseEntity handlePhoneNumberIsUsedException() {
        String error = "Account with this phone number already exists!";
        return ResponseEntity.badRequest().body(error);
    }

    @ExceptionHandler(EmailIsUsedException.class)
    public ResponseEntity handleEmailIsUsedException() {
        String error = "Account with this email already exists!";
        return ResponseEntity.badRequest().body(error);
    }

    @ExceptionHandler(NotFoundInDataBaseException.class)
    public ResponseEntity handleNotFoundInDataBaseException() {
        String error = "Something went wrong on our server. Please, try again later.";
        return ResponseEntity.status(500).body(error);
    }
}
