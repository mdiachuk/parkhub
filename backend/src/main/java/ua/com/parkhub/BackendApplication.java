package ua.com.parkhub;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication

public class BackendApplication {
    /*@Bean
    CommandLineRunner init(CustomerService customerService) {
        return args -> {
            CustomerModel customerModel = customerService.findCustomerByPhoneNumberOrAdd("+380672202222");
            System.out.println(customerModel);
        };
    }*/

    public static void main(String[] args) {
        SpringApplication.run(BackendApplication.class, args);
    }
}
