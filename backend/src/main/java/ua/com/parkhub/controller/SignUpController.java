package ua.com.parkhub.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ua.com.parkhub.dto.ManagerRegistrationDataDTO;
import ua.com.parkhub.exceptions.EmailException;
import ua.com.parkhub.exceptions.NotFoundInDataBaseException;
import ua.com.parkhub.exceptions.PhoneNumberException;
import ua.com.parkhub.mappers.dtoToModel.ManagerRegistrationRequestDtoToModel;
import ua.com.parkhub.service.impl.SignUpService;
import ua.com.parkhub.validation.groups.CustomerChecks;
import ua.com.parkhub.validation.groups.ManagerChecks;
import ua.com.parkhub.validation.groups.UserChecks;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/signup")
public class SignUpController {
    private static final Logger logger = LoggerFactory.getLogger(SignUpController.class);

    private final SignUpService signUpService;
    private final ManagerRegistrationRequestDtoToModel managerRegistrationRequestDtoToModel;

    @Autowired
    public SignUpController(SignUpService signUpService,
                            ManagerRegistrationRequestDtoToModel managerRegistrationRequestDtoToModel) {
        this.signUpService = signUpService;
        this.managerRegistrationRequestDtoToModel = managerRegistrationRequestDtoToModel;
    }

    @PostMapping(value = "/manager")
    public ResponseEntity registerManager(@RequestBody @Validated({CustomerChecks.class, UserChecks.class,
            ManagerChecks.class}) ManagerRegistrationDataDTO manager, BindingResult result) {
        if (result.hasErrors()) {
            List<String> errors = result.getAllErrors().stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .collect(Collectors.toList());
            logger.info("Validation errors: {}", errors);
            return ResponseEntity.badRequest().body(errors);
        }
        signUpService.registerManager(managerRegistrationRequestDtoToModel.transform(manager));
        logger.info("Manager registration request created");
        return ResponseEntity.ok().build();
    }

    @ExceptionHandler(PhoneNumberException.class)
    public ResponseEntity handlePhoneNumberIsUsedException(PhoneNumberException e) {
        String message = e.getMessage();
        logger.info(message);
        return ResponseEntity.badRequest().body(message);
    }

    @ExceptionHandler(EmailException.class)
    public ResponseEntity handleEmailIsUsedException(EmailException e) {
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

//    /**
//     *
//     * @param user
//     * @return 200 Status if new User be create
//     *         500 Status if new User not be create
//     */
//    @PostMapping ("/user")
//    public ResponseEntity create(@RequestBody User user) {
//        if (signUpService.createUser(user)) {
//            userService.sendToken(user.getEmail(), UuidTokenType.EMAIL);
//            return ResponseEntity.ok().build();
//        } else {
//            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//
//    }
}
