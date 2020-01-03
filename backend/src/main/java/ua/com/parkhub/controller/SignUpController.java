package ua.com.parkhub.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ua.com.parkhub.dto.ManagerRegistrationDataDTO;
import ua.com.parkhub.exceptions.EmailIsUsedException;
import ua.com.parkhub.exceptions.NotFoundInDataBaseException;
import ua.com.parkhub.exceptions.PhoneNumberIsUsedException;
import ua.com.parkhub.persistence.entities.User;
import ua.com.parkhub.service.impl.AddressGeoService;
import ua.com.parkhub.service.impl.SignUpService;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/signup")
public class SignUpController {

    private static final Logger logger = LoggerFactory.getLogger(SignUpController.class);

    private final SignUpService signUpService;

    @Autowired
    public SignUpController(SignUpService signUpService) {
        this.signUpService = signUpService;
    }

    @PostMapping(value = "/manager")
    public ResponseEntity registerManager(@RequestBody @Valid ManagerRegistrationDataDTO manager, BindingResult result) {
        if (result.hasErrors()) {
            List<String> errors = result.getAllErrors().stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .collect(Collectors.toList());
            logger.info("Validation errors: {}", errors);
            return ResponseEntity.badRequest().body(errors);
        }
        signUpService.registerManager(manager);
        logger.info("Manager registration request created");
        return ResponseEntity.ok().build();
    }

    @ExceptionHandler(PhoneNumberIsUsedException.class)
    public ResponseEntity handlePhoneNumberIsUsedException(PhoneNumberIsUsedException e) {
        String message = e.getMessage();
        logger.info(message);
        return ResponseEntity.badRequest().body(message);
    }

    @ExceptionHandler(EmailIsUsedException.class)
    public ResponseEntity handleEmailIsUsedException(EmailIsUsedException e) {
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



    /**
     *
     * @param user
     * @return 200 Status if new User be create
     *         500 Status if new User not be create
     */
    @PostMapping ("/user")
    public ResponseEntity create(@RequestBody User user) {
//        Example example = new Example();
////        try {
////            example.getPAram();
////        } catch (UnsupportedEncodingException e) {
////            e.printStackTrace();
////        }
        AddressGeoService addressGeoService = new AddressGeoService();
        addressGeoService.getLatLon("київ, леся курбаса 5а");
        if (signUpService.createUser(user)){
            return ResponseEntity.ok().build();
        } else {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }


}
