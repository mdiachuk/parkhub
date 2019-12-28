package ua.com.parkhub.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ua.com.parkhub.dto.EmailDTO;
import ua.com.parkhub.dto.PasswordDTO;
import ua.com.parkhub.exceptions.EmailException;
import ua.com.parkhub.exceptions.InvalidTokenException;
import ua.com.parkhub.exceptions.NotFoundInDataBaseException;
import ua.com.parkhub.service.impl.UserService;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/send-token-to-email")
    public ResponseEntity sendToken(@RequestBody @Valid EmailDTO email, BindingResult result) {
        if (result.hasFieldErrors()) {
            List<String> errors = result.getAllErrors().stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .collect(Collectors.toList());
            logger.info("Validation errors: {}", errors);
            return ResponseEntity.badRequest().body(errors);
        }
        userService.sendToken(email);
        logger.info("ok");
        return ResponseEntity.ok().build();
    }

    @PostMapping("/password")
    public ResponseEntity resetPassword(@RequestBody @Valid PasswordDTO password, BindingResult result) {
        if (result.hasFieldErrors()) {
            List<String> errors = result.getAllErrors().stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .collect(Collectors.toList());
            logger.info("Validation errors: {}", errors);
            return ResponseEntity.badRequest().body(errors);
        }
        userService.resetPassword(password);
        logger.info("ok");
        return ResponseEntity.ok().build();
    }

    @ExceptionHandler(EmailException.class)
    public ResponseEntity handleEmailException(EmailException e) {
        String message = e.getMessage();
        logger.info(message);
        return ResponseEntity.badRequest().body(message);
    }

    @ExceptionHandler(InvalidTokenException.class)
    public ResponseEntity handleUuidTokenException(InvalidTokenException e) {
        String message = e.getMessage();
        logger.info(message);
        return ResponseEntity.badRequest().body(message);
    }

    @ExceptionHandler(NotFoundInDataBaseException.class)
    public ResponseEntity handleNotFoundInDataBaseException(NotFoundInDataBaseException e) {
        logger.info(e.getMessage());
        String message = "Something went wrong on our server. Please, try again later.";
        return ResponseEntity.status(500).body(message);
    }
}