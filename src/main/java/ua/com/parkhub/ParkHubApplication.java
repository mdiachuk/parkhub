package ua.com.parkhub;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class ParkHubApplication {
    @RequestMapping("/")
    public String greet(){
        return "welcome!";
    }

    public static void main(String[] args) {
        SpringApplication.run(ParkHubApplication.class, args);
    }

}
