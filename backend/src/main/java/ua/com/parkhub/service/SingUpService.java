package ua.com.parkhub.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.com.parkhub.persistence.entities.User;
import ua.com.parkhub.persistence.impl.UserDAO;


@Service
@Transactional
public class SingUpService {

    @Autowired
    public UserDAO userDAO;

    public HttpStatus createUser(User user) {
        System.out.println(userDAO.haveEmail(user.getEmail()));
        System.out.println(userDAO.havePhoneNumber(user.getCustomer().getPhoneNumber()));
        if  (userDAO.haveEmail(user.getEmail())||
                userDAO.havePhoneNumber(user.getCustomer().getPhoneNumber())){
            System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&");
            return HttpStatus.INTERNAL_SERVER_ERROR;
        } else {
            System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
            userDAO.addElement(user);
            return HttpStatus.OK;
        }

    }

}
