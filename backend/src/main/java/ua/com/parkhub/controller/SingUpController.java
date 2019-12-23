package ua.com.parkhub.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.com.parkhub.persistence.entities.User;
import ua.com.parkhub.service.SingUpService;

@RestController
@RequestMapping({"/api/v1/singup"})
public class SingUpController {

    @Autowired
    private SingUpService singUpService;

    @Autowired
    private User user;

    /**
     *
     * @param user
     * @return 200 Status if new User be create
     *         500 Status if new User not be create
     */
    @PostMapping ("/user")
    public ResponseEntity create(@RequestBody User user) {

        if (singUpService.createUser(user)){
            return ResponseEntity.ok().build();
        } else {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

}
