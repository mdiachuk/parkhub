package ua.com.parkhub.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.com.parkhub.dto.LoginDTO;
import ua.com.parkhub.dto.UserDTO;
import ua.com.parkhub.security.JwtUtil;
import ua.com.parkhub.service.AuthorizationService;

import javax.validation.Valid;


@RestController
@RequestMapping(value = "")
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
        response.setToken(jwtUtil.generateToken(response.getEmail(), response.getRole().toString(), response.getId()));
        return ResponseEntity.ok(response);
    }

}
