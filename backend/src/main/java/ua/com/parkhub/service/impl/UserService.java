package ua.com.parkhub.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ua.com.parkhub.exceptions.PasswordException;
import ua.com.parkhub.model.UserModel;
import ua.com.parkhub.persistence.impl.UserDAO;
import ua.com.parkhub.service.IUserService;


import java.util.Optional;


@Service
public class UserService implements IUserService {
    private static final Logger logger = LoggerFactory.getLogger(SignUpService.class);
    private UserDAO userDAO;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserDAO userDAO, PasswordEncoder passwordEncoder){
        this.userDAO = userDAO;
        this.passwordEncoder = passwordEncoder;
    }

    public Optional<UserModel> findUserById(long id){
        return userDAO.findElementById(id);
    }

    @Override
    public void updateUser(long id, UserModel userUp) {
        UserModel userModel = findUserById(id).get();
        userModel.setFirstName(userUp.getFirstName());
        userModel.setLastName(userUp.getLastName());
        userModel.setEmail(userUp.getEmail());
        userModel.getCustomer().setPhoneNumber(userUp.getCustomer().getPhoneNumber());
        userDAO.updateElement(userModel);
        logger.info("User information was updated");
    }

    @Override
    public void changePassword(long id, String newPassword, UserModel userModel){
        UserModel user = findUserById(id).get();
        if (passwordEncoder.matches(userModel.getPassword(), user.getPassword())){
            user.setPassword(passwordEncoder.encode(newPassword));
            userDAO.updateElement(user);
            logger.info("Password was updated");
        } else {
            logger.info("Password was not updated");
            new PasswordException();
        }
    }
}
