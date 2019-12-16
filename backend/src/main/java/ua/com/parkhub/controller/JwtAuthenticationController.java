package ua.com.parkhub.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.com.parkhub.dto.LoginDTO;
import ua.com.parkhub.dto.UserDTO;
import ua.com.parkhub.security.JwtUtil;
import ua.com.parkhub.service.AuthorizationService;
import ua.com.parkhub.values.Constants;

import javax.validation.Valid;


@RestController
@RequestMapping(value = "/api")
@CrossOrigin
public class JwtAuthenticationController {

    private AuthorizationService authenticationService;
    private static Logger logger = LoggerFactory.getLogger(JwtAuthenticationController.class.getSimpleName());
    private JwtUtil jwtUtil;


    @Autowired
    public JwtAuthenticationController(AuthorizationService authenticationService, JwtUtil jwtUtil) {
        this.authenticationService = authenticationService;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping(value = "/login")
    public ResponseEntity<UserDTO> loginUser(@Valid @RequestBody LoginDTO login) {
        UserDTO response = authenticationService.loginUser(login);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);

        httpHeaders.add(Constants.TOKEN_HEADER, jwtUtil.generateToken(response.getEmail(), response.getRole().toString(), response.getId()));
        return new ResponseEntity<>(response, httpHeaders, HttpStatus.OK);
    }
}
