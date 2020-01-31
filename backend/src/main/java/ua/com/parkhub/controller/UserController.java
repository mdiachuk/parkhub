package ua.com.parkhub.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ua.com.parkhub.dto.*;
import ua.com.parkhub.mappers.Mapper;
import ua.com.parkhub.model.UserModel;
import ua.com.parkhub.service.IUserService;

import javax.validation.Valid;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@RestController
public class UserController {

    private static final Logger logger = Logger.getLogger(UserController.class.getName());

    private static final String VALIDATION_ERRORS = "Validation errors: %s";

    private final IUserService userService;
    private final Mapper<UserModel, UserInfoDTO> userModelToUserInfoDTOMapper;
    private final Mapper<PasswordDTO, UserModel> passwordDTOtoUserModelMapper;
    private final Mapper<UserInfoDTO, UserModel> userInfoDTOtoUserModelMapper;

    @Autowired
    public UserController(IUserService userService, Mapper<UserModel, UserInfoDTO> userModelToUserInfoDTOMapper,
                          Mapper<PasswordDTO, UserModel> passwordDTOtoUserModelMapper,
                          Mapper<UserInfoDTO, UserModel> userInfoDTOtoUserModelMapper){
        this.userService = userService;
        this.userModelToUserInfoDTOMapper = userModelToUserInfoDTOMapper;
        this.passwordDTOtoUserModelMapper = passwordDTOtoUserModelMapper;
        this.userInfoDTOtoUserModelMapper = userInfoDTOtoUserModelMapper;
    }


    @PostMapping("/api/user/token")
    public ResponseEntity sendToken(@RequestBody @Valid EmailDTO emailDTO, BindingResult result) {
        if (result.hasFieldErrors()) {
            List<String> errors = result.getAllErrors().stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .collect(Collectors.toList());
            logger.info(String.format(VALIDATION_ERRORS, errors));
            return ResponseEntity.badRequest().body(errors);
        }
        userService.sendToken(emailDTO.getEmail(), emailDTO.getTokenType());
        return ResponseEntity.ok().build();
    }


    @PostMapping("/api/user/token/refresh")
    public ResponseEntity resendToken(@RequestBody @Valid TokenDTO tokenDTO, BindingResult result) {
        if (result.hasFieldErrors()) {
            List<String> errors = result.getAllErrors().stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .collect(Collectors.toList());
            logger.info(String.format(VALIDATION_ERRORS, errors));
            return ResponseEntity.badRequest().body(errors);
        }
        userService.resendToken(tokenDTO.getToken(), tokenDTO.getTokenType());
        return ResponseEntity.ok().build();
    }


    @GetMapping("/api/user/token/{token}")
    public ResponseEntity checkToken(@PathVariable("token") String token) {
        if (userService.isLinkActive(token)) {
            logger.info("Link is active");
            return ResponseEntity.ok().build();
        } else {
            logger.info("Link expired");
            return ResponseEntity.badRequest().body("Link is not active anymore");
        }
    }


    @PostMapping("/api/user/verify")
    public ResponseEntity verifyEmail(@RequestBody String token) {
        userService.verifyEmail(token);
        return ResponseEntity.ok().build();
    }


    @PostMapping("/api/user/password/reset")
    public ResponseEntity resetPassword(@RequestBody @Valid ResetPasswordDTO passwordDTO, BindingResult result) {
        if (result.hasFieldErrors()) {
            List<String> errors = result.getAllErrors().stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .collect(Collectors.toList());
            logger.info(String.format(VALIDATION_ERRORS, errors));
            return ResponseEntity.badRequest().body(errors);
        }
        userService.resetPassword(passwordDTO.getToken(), passwordDTO.getPassword());
        return ResponseEntity.ok().build();
    }


    @PreAuthorize("hasRole('ADMIN') or hasRole('MANAGER') or hasRole('USER')")
    @GetMapping("/api/user/{id}")
    @ResponseBody
    public ResponseEntity<UserInfoDTO> findUserById(@PathVariable Long id){
        return ResponseEntity.ok(userModelToUserInfoDTOMapper.transform(userService.findUserById(id)));
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('MANAGER') or hasRole('USER')")
    @PostMapping("/api/user/{id}")
    public ResponseEntity<Void> updateUser(@PathVariable Long id, @RequestBody UserInfoDTO userInfoDTO){
        userService.updateUser(id, userInfoDTOtoUserModelMapper.transform(userInfoDTO));
        return ResponseEntity.ok().build();
    }


    @PreAuthorize("hasRole('ADMIN') or hasRole('MANAGER') or hasRole('USER')")
    @PostMapping("/api/user/password/{id}")
    public ResponseEntity<Void> updateUserPassword(@PathVariable Long id, @RequestBody PasswordDTO passwordDTO){
        userService.changePassword(id, passwordDTO.getNewPassword(),
                    passwordDTOtoUserModelMapper.transform(passwordDTO));
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }
}
