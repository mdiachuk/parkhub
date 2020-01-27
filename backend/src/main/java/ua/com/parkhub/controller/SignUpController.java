package ua.com.parkhub.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ua.com.parkhub.dto.ManagerRegistrationDataDTO;
import ua.com.parkhub.dto.UserDTO;
import ua.com.parkhub.mappers.Mapper;
import ua.com.parkhub.model.ManagerRegistrationDataModel;
import ua.com.parkhub.model.UserModel;
import ua.com.parkhub.model.enums.UuidTokenType;
import ua.com.parkhub.service.ISignUpService;
import ua.com.parkhub.service.IUserService;
import ua.com.parkhub.validation.groups.CustomerChecks;
import ua.com.parkhub.validation.groups.ManagerChecks;
import ua.com.parkhub.validation.groups.UserChecks;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/signup")
public class SignUpController {

    private static final Logger logger = LoggerFactory.getLogger(SignUpController.class);

    private static final String VALIDATION_ERRORS = "Validation errors: {}";

    private final ISignUpService signUpService;
    private final IUserService userService;
    private final Mapper<ManagerRegistrationDataDTO, ManagerRegistrationDataModel>
            managerRegistrationRequestDtoToModel;
    private final Mapper<UserDTO, UserModel> userDtoToUserModelMapper;

    @Autowired
    public SignUpController(ISignUpService signUpService, IUserService userService,
                            Mapper<UserDTO, UserModel> userDtoToUserModelMapper,
                            Mapper<ManagerRegistrationDataDTO, ManagerRegistrationDataModel>
                                        managerRegistrationRequestDtoToModel) {
        this.signUpService = signUpService;
        this.managerRegistrationRequestDtoToModel = managerRegistrationRequestDtoToModel;
        this.userDtoToUserModelMapper = userDtoToUserModelMapper;
        this.userService = userService;
    }

    @PostMapping(value = "/manager")
    public ResponseEntity registerManager(@RequestBody @Validated({CustomerChecks.class, UserChecks.class,
            ManagerChecks.class}) ManagerRegistrationDataDTO manager, BindingResult result) {
        if (result.hasErrors()) {
            List<String> errors = result.getAllErrors().stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .collect(Collectors.toList());
            logger.info(VALIDATION_ERRORS, errors);
            return ResponseEntity.badRequest().body(errors);
        }
        signUpService.registerManager(managerRegistrationRequestDtoToModel.transform(manager));
        logger.info("Manager registration request created");
        return ResponseEntity.ok().build();
    }


    @PostMapping ("/user")
    public ResponseEntity registerUser(@RequestBody UserDTO userDTO) {
        if ( signUpService.signUpUser(userDtoToUserModelMapper.transform(userDTO))){
            userService.sendToken(userDTO.getEmail(), UuidTokenType.EMAIL.getType());
            logger.info("New User signup");
            return ResponseEntity.ok().build();
        } else {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
