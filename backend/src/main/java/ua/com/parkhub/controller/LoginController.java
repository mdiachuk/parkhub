package ua.com.parkhub.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.sql.SQLOutput;
import java.util.LinkedHashMap;

@RestController
public class LoginController {
    @RequestMapping("/home1")
    public Principal user(OAuth2Authentication user) {
        LinkedHashMap<String,String> o = (LinkedHashMap<String,String>) user.getUserAuthentication().getDetails();
        String b = o.get("email");
//        ((OAuth2Authentication) user).getUserAuthentication().getDetails();
        System.out.println(b);


        return user;
    }
}
