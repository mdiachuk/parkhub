package ua.com.parkhub.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.com.parkhub.dto.LoginDTO;
import ua.com.parkhub.dto.UserDTO;
import ua.com.parkhub.exceptions.PermissionException;
import ua.com.parkhub.exceptions.StatusCode;
import ua.com.parkhub.mappers.dtoToModel.LoginDtoToUserModelMapper;
import ua.com.parkhub.mappers.modelToDTO.UserModelToUserDtoMapper;
import ua.com.parkhub.security.JwtUtil;
import ua.com.parkhub.service.IAuthorizationService;

import javax.validation.Valid;


@RestController
@RequestMapping(value = "")
@CrossOrigin
public class JwtAuthenticationController {

    private IAuthorizationService authenticationService;
    private static Logger logger = LoggerFactory.getLogger(JwtAuthenticationController.class.getSimpleName());
    private JwtUtil jwtUtil;
    private UserModelToUserDtoMapper userModelToUserDtoMapper;
    private LoginDtoToUserModelMapper loginDtoToUserModelMapper;

    @Autowired
    public JwtAuthenticationController(IAuthorizationService authenticationService, JwtUtil jwtUtil, UserModelToUserDtoMapper userModelToUserDtoMapper, LoginDtoToUserModelMapper loginDtoToUserModelMapper) {
        this.authenticationService = authenticationService;
        this.jwtUtil = jwtUtil;
        this.userModelToUserDtoMapper = userModelToUserDtoMapper;
        this.loginDtoToUserModelMapper = loginDtoToUserModelMapper;
    }

    @PostMapping(value = "/api/login")
    public ResponseEntity<UserDTO> loginUser(@Valid @RequestBody LoginDTO login) {
        if (login == null){ throw new PermissionException(StatusCode.NO_ACCOUNT_FOUND); }
        UserDTO response = userModelToUserDtoMapper.transform(authenticationService.loginUser(loginDtoToUserModelMapper.transform(login)));
        response.setToken(jwtUtil.generateToken(response.getEmail(), response.getRole().toString(), response.getId()));
        return ResponseEntity.ok(response);
    }

    @ExceptionHandler(PermissionException.class)
    public ResponseEntity handlePermissionException(PermissionException e) {
        return ResponseEntity.badRequest().body(e.getStatusCode());
    }
}
