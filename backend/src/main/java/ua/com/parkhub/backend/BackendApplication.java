package ua.com.parkhub.backend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ua.com.parkhub.backend.service.SingUpService;

@SpringBootApplication
public class BackendApplication {

    @Autowired
    SingUpService singUpService;

    public static void main(String[] args) {
        SpringApplication.run(BackendApplication.class, args);

    }

}
