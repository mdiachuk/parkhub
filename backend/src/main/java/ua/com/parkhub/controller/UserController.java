package ua.com.parkhub.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ua.com.parkhub.dto.*;
import ua.com.parkhub.exceptions.EmailException;
import ua.com.parkhub.exceptions.InvalidTokenException;
import ua.com.parkhub.exceptions.NotFoundInDataBaseException;
import ua.com.parkhub.exceptions.PasswordException;
import ua.com.parkhub.mappers.dtoToModel.PasswordDTOtoUserModelMapper;
import ua.com.parkhub.mappers.dtoToModel.UserDtoToUserModelMapper;
import ua.com.parkhub.mappers.dtoToModel.UserInfoDTOtoUserModelMapper;
import ua.com.parkhub.mappers.modelToDto.UserModelToUserInfoDTOMapper;
import ua.com.parkhub.model.UuidTokenType;
import ua.com.parkhub.service.impl.UserService;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    private final UserService userService;
    UserModelToUserInfoDTOMapper userModelToUserInfoDTOMapper;
    UserDtoToUserModelMapper userDTOtoModelMapper;
    PasswordDTOtoUserModelMapper passwordDTOtoUserModelMapper;
    UserInfoDTOtoUserModelMapper userInfoDTOtoUserModelMapper;

    @Autowired
    public UserController(UserService userService, UserModelToUserInfoDTOMapper userModelToUserInfoDTOMapper,
                          UserDtoToUserModelMapper userDTOtoModelMapper,
                          PasswordDTOtoUserModelMapper passwordDTOtoUserModelMapper,
                          UserInfoDTOtoUserModelMapper userInfoDTOtoUserModelMapper){
        this.userService = userService;
        this.userModelToUserInfoDTOMapper = userModelToUserInfoDTOMapper;
        this.userDTOtoModelMapper = userDTOtoModelMapper;
        this.passwordDTOtoUserModelMapper = passwordDTOtoUserModelMapper;
        this.userInfoDTOtoUserModelMapper = userInfoDTOtoUserModelMapper;
    }


    @PostMapping("/api/user-util/send-token-to-email")
    public ResponseEntity sendToken(@RequestBody @Valid EmailDTO emailDTO, BindingResult result) {
        if (result.hasFieldErrors()) {
            List<String> errors = result.getAllErrors().stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .collect(Collectors.toList());
            logger.info("Validation errors: {}", errors);
            return ResponseEntity.badRequest().body(errors);
        }
        String email = emailDTO.getEmail();
        UuidTokenType type = UuidTokenType.valueOf(emailDTO.getTokenType());
        userService.sendToken(email, type);
        return ResponseEntity.ok().build();
    }


    @PostMapping("/api/user-util/resend-token-to-email")
    public ResponseEntity resendToken(@RequestBody @Valid TokenDTO tokenDTO, BindingResult result) {
        if (result.hasFieldErrors()) {
            List<String> errors = result.getAllErrors().stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .collect(Collectors.toList());
            logger.info("Validation errors: {}", errors);
            return ResponseEntity.badRequest().body(errors);
        }
        String token = tokenDTO.getToken();
        UuidTokenType type = UuidTokenType.valueOf(tokenDTO.getTokenType());
        userService.resendToken(token, type);
        return ResponseEntity.ok().build();
    }


    @GetMapping("/api/user-util/check-token/{token}")
    public ResponseEntity checkToken(@PathVariable("token") String token) {
        if (userService.isLinkActive(token)) {
            logger.info("Link is active");
            return ResponseEntity.ok().build();
        } else {
            logger.info("Link expired");
            return ResponseEntity.badRequest().body("Link is not active anymore");
        }
    }


    @PostMapping("/api/user-util/verify-email")
    public ResponseEntity verifyEmail(@RequestBody String token) {
        userService.verifyEmail(token);
        return ResponseEntity.ok().build();
    }


    @PostMapping("/api/user-util/reset-password")
    public ResponseEntity resetPassword(@RequestBody @Valid ResetPasswordDTO passwordDTO, BindingResult result) {
        if (result.hasFieldErrors()) {
            List<String> errors = result.getAllErrors().stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .collect(Collectors.toList());
            logger.info("Validation errors: {}", errors);
            return ResponseEntity.badRequest().body(errors);
        }
        userService.resetPassword(passwordDTO.getToken(), passwordDTO.getPassword());
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







    @PreAuthorize("hasRole('ADMIN') or hasRole('MANAGER') or hasRole('USER')")
    @GetMapping("/api/user/{id}")
    @ResponseBody
    public ResponseEntity<UserInfoDTO> findUserById(@PathVariable Long id){
        return ResponseEntity.ok(userModelToUserInfoDTOMapper.transform(userService.findUserById(id).get()));
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('MANAGER') or hasRole('USER')")
    @PostMapping("/api/user/{id}")
    public ResponseEntity updateUser(@PathVariable Long id, @RequestBody UserInfoDTO userInfoDTO){
        userService.updateUser(id, userInfoDTOtoUserModelMapper.transform(userInfoDTO));
        return ResponseEntity.ok().build();
    }


    @PreAuthorize("hasRole('ADMIN') or hasRole('MANAGER') or hasRole('USER')")
    @PostMapping("/api/user/password/{id}")
    public ResponseEntity<Void> updateUserPassword(@PathVariable Long id, @RequestBody PasswordDTO passwordDTO){
        try {
            userService.changePassword(id, passwordDTO.getNewPassword(),
                    passwordDTOtoUserModelMapper.transform(passwordDTO));
        } catch (PasswordException ex){
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
        }
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }
}
