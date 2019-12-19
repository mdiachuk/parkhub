package ua.com.parkhub.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ua.com.parkhub.persistence.entities.User;
import ua.com.parkhub.service.SingUpService;

@RestController
@RequestMapping({"/api/v1"})
public class SingUpController {

    @Autowired
    private SingUpService singUpService;

    @Autowired
    private User user;
    
    @PostMapping ("/singup")
    public HttpStatus create(@RequestBody User user) {
        HttpStatus s = singUpService.createUser(user);
        System.out.println(s);
        return HttpStatus.EXPECTATION_FAILED;

    }

}
