package ua.com.parkhub.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.com.parkhub.backend.dao.impl.UserDAO;
import ua.com.parkhub.backend.persistense.entities.Customer;
import ua.com.parkhub.backend.persistense.entities.User;



@Service
@Transactional
public class SingUpService {

    @Autowired
    public UserDAO userDAO;


    public String createUser(User user) {

//        User user1 = new User();

        Customer customer = new Customer();
        customer.setActive(true);
        customer.setPhoneNumber("+380686868686886");

//        user1.setPass("qwertyuiop");
//        user1.setEmail("qwertyui@dg.ru");
//        user1.setSecondname("qwtefqwtdfqwudfuqw");
//        user1.setName("OLOLOL");
//        user1.setRole(1);

        System.out.println(user+"!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");

        user.setCustomer(customer);
        System.out.println(user+"####################################################################################################");

        userDAO.createUser(user);
        System.out.println(user);
        return null;
    }


}
