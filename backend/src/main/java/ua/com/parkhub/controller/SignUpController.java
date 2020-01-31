package ua.com.parkhub.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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
import java.util.logging.Logger;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/signup")
public class SignUpController {

    private static final Logger logger = Logger.getLogger(SignUpController.class.getName());

    private static final String VALIDATION_ERRORS = "Validation errors: %s";

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

    @PostMapping("/user")
    public ResponseEntity registerUser(@RequestBody @Validated({CustomerChecks.class,
            UserChecks.class}) UserDTO user, BindingResult result) {
        if (result.hasErrors()) {
            List<String> errors = result.getAllErrors().stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .collect(Collectors.toList());
            logger.info(String.format(VALIDATION_ERRORS, errors));
            return ResponseEntity.badRequest().body(errors);
        }
        signUpService.registerUser(userDtoToUserModelMapper.transform(user));
        userService.sendToken(user.getEmail(), UuidTokenType.EMAIL.getType());
        logger.info("User created");
        return ResponseEntity.ok().build();
    }

    @PostMapping(value = "/manager")
    public ResponseEntity registerManager(@RequestBody @Validated({CustomerChecks.class, UserChecks.class,
            ManagerChecks.class}) ManagerRegistrationDataDTO manager, BindingResult result) {
        if (result.hasErrors()) {
            List<String> errors = result.getAllErrors().stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .collect(Collectors.toList());
            logger.info(String.format(VALIDATION_ERRORS, errors));
            return ResponseEntity.badRequest().body(errors);
        }
        signUpService.registerManager(managerRegistrationRequestDtoToModel.transform(manager));
        logger.info("Manager registration request created");
        return ResponseEntity.ok().build();
    }
}
