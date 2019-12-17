package ua.com.parkhub.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.com.parkhub.backend.persistense.entities.User;
import ua.com.parkhub.backend.service.SingUpService;

@RestController
@RequestMapping({"/api/singup"})
public class SingUpController {

    @Autowired
    private SingUpService singUpService;
    @Autowired
    private User user;


    @PostMapping
    public String create(@RequestBody User user) {
        System.out.println(user.getName().toString());
        singUpService.createUser(user);

        return "User create";

    }

}
