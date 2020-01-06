package ua.com.parkhub.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ua.com.parkhub.dto.CustomerDTO;
import ua.com.parkhub.dto.ManagerRegistrationDataDTO;
import ua.com.parkhub.dto.UserDTO;
import ua.com.parkhub.exceptions.EmailException;
import ua.com.parkhub.exceptions.NotFoundInDataBaseException;
import ua.com.parkhub.exceptions.PhoneNumberException;
import ua.com.parkhub.mappers.fromDtoToModel.CustomerDtoToCustomerModelMapper;
import ua.com.parkhub.mappers.fromDtoToModel.UserDtoToUserModelMapper;
import ua.com.parkhub.persistence.entities.User;
import ua.com.parkhub.service.impl.SignUpService;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/signup")
public class SignUpController {
    private static final Logger logger = LoggerFactory.getLogger(SignUpController.class);

    private final SignUpService signUpService;
    private final CustomerDtoToCustomerModelMapper customerDtoToCustomerModelMapper;
    private final UserDtoToUserModelMapper userDtoToUserModelMapper;

    @Autowired
    public SignUpController(SignUpService signUpService,
                            CustomerDtoToCustomerModelMapper customerDtoToCustomerModelMapper,
                            UserDtoToUserModelMapper userDtoToUserModelMapper) {
        this.signUpService = signUpService;
        this.customerDtoToCustomerModelMapper = customerDtoToCustomerModelMapper;
        this.userDtoToUserModelMapper = userDtoToUserModelMapper;
    }

    //
//    @PostMapping(value = "/manager")
//    public ResponseEntity registerManager(@RequestBody @Valid ManagerRegistrationDataDTO manager, BindingResult result) {
//        if (result.hasErrors()) {
//            List<String> errors = result.getAllErrors().stream()
//                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
//                    .collect(Collectors.toList());
//            logger.info("Validation errors: {}", errors);
//            return ResponseEntity.badRequest().body(errors);
//        }
//        signUpService.registerManager(manager);
//        logger.info("Manager registration request created");
//        return ResponseEntity.ok().build();
//    }
//
    @PostMapping(value = "/create-customer")
    public ResponseEntity createCustomer(@RequestBody CustomerDTO customerDTO) {
        signUpService.createCustomer(customerDtoToCustomerModelMapper.transform(customerDTO));
        return ResponseEntity.ok().build();
    }

    @PostMapping(value = "/create-user")
    public ResponseEntity createUser(@RequestBody UserDTO userDTO) {
        signUpService.createUser(userDtoToUserModelMapper.transform(userDTO));
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
//
//
//
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
//
//
}
