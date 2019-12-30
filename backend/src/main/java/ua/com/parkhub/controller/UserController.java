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
import ua.com.parkhub.dto.TokenDTO;
import ua.com.parkhub.exceptions.EmailException;
import ua.com.parkhub.exceptions.InvalidTokenException;
import ua.com.parkhub.exceptions.NotFoundInDataBaseException;
import ua.com.parkhub.model.UuidTokenTypeModel;
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
    public ResponseEntity sendToken(@RequestBody @Valid EmailDTO emailDTO, BindingResult result) {
        if (result.hasFieldErrors()) {
            List<String> errors = result.getAllErrors().stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .collect(Collectors.toList());
            logger.info("Validation errors: {}", errors);
            return ResponseEntity.badRequest().body(errors);
        }
        String email = emailDTO.getEmail();
        UuidTokenTypeModel type = UuidTokenTypeModel.valueOf(emailDTO.getTokenType());
        userService.sendToken(email, type);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/resend-token-to-email")
    public ResponseEntity resendToken(@RequestBody @Valid TokenDTO tokenDTO, BindingResult result) {
        if (result.hasFieldErrors()) {
            List<String> errors = result.getAllErrors().stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .collect(Collectors.toList());
            logger.info("Validation errors: {}", errors);
            return ResponseEntity.badRequest().body(errors);
        }
        String token = tokenDTO.getToken();
        UuidTokenTypeModel type = UuidTokenTypeModel.valueOf(tokenDTO.getTokenType());
        userService.resendToken(token, type);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/check-token/{token}")
    public ResponseEntity checkToken(@PathVariable("token") String token) {
        if (userService.isLinkActive(token)) {
            logger.info("Link is active");
            return ResponseEntity.ok().build();
        } else {
            logger.info("Link expired");
            return ResponseEntity.badRequest().body("Link is not active anymore");
        }
    }

    @PostMapping("/verify-email")
    public ResponseEntity verifyEmail(@RequestBody String token) {
        userService.verifyEmail(token);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/reset-password")
    public ResponseEntity resetPassword(@RequestBody @Valid PasswordDTO password, BindingResult result) {
        if (result.hasFieldErrors()) {
            List<String> errors = result.getAllErrors().stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .collect(Collectors.toList());
            logger.info("Validation errors: {}", errors);
            return ResponseEntity.badRequest().body(errors);
        }
        userService.resetPassword(password);
        logger.info("Link with unique token was sent");
        return ResponseEntity.ok().build();
    }

    @ExceptionHandler(EmailException.class)
    public ResponseEntity handleEmailException(EmailException e) {
        String message = e.getMessage();
        logger.info(message);
        return ResponseEntity.badRequest().body(message);
    }

    @ExceptionHandler(InvalidTokenException.class)
    public ResponseEntity handleInvalidTokenException(InvalidTokenException e) {
        String message = e.getMessage();
        logger.info(message);
        return ResponseEntity.badRequest().body(message);
    }

    @ExceptionHandler(NotFoundInDataBaseException.class)
    public ResponseEntity handleNotFoundInDataBaseException(NotFoundInDataBaseException e) {
        logger.info(e.getMessage());
        String message = "Invalid link";
        return ResponseEntity.status(500).body(message);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity handleNotFoundInDataBaseException(IllegalArgumentException e) {
        logger.info(e.getMessage());
        String message = "Something went wrong on our server. Please, try again later.";
        return ResponseEntity.status(500).body(message);
    }
}