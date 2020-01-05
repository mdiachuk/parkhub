package ua.com.parkhub.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class LoginController {
    @RequestMapping("/home1")
    public Principal user(Principal principal) {
        return principal;
    }
}
