package ua.com.parkhub.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ua.com.parkhub.dto.LoginDTO;
import ua.com.parkhub.dto.UserDTO;
import ua.com.parkhub.exceptions.PermissionException;
import ua.com.parkhub.mappers.UserMapper;
import ua.com.parkhub.persistence.entities.User;
import ua.com.parkhub.persistence.impl.UserDAO;
import ua.com.parkhub.service.AuthorizationService;

import java.util.Optional;

@Service
public class AuthorizationServiceImpl implements AuthorizationService {

    private UserDAO userDAO;
    private PasswordEncoder passwordEncoder;

    public AuthorizationServiceImpl() {
    }

    @Autowired
    public AuthorizationServiceImpl(UserDAO userDAO, PasswordEncoder passwordEncoder) {
        this.userDAO = userDAO;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDTO loginUser(LoginDTO user) {
        Optional<User> userEntity = userDAO.findUserByEmail(user.getEmail());
        if (userEntity.get().getNumber_of_faild_pass_entering() >= 3){
            // ДОБАВИТЬ В ТАБЛИЧКУ ЗАБЛОКАНИХ ЮЗЕРІВ
            throw new PermissionException("Your account was blocked for 24 hours because of 3 unsuccessful tries to login. Please, try again later.");
        } else {
            if (userEntity.filter(
                    userEnt -> passwordEncoder.matches(user.getPassword(), userEnt.getPassword())
            ).isPresent()) {
                return userEntity.filter(
                        userEnt -> passwordEncoder.matches(user.getPassword(), userEnt.getPassword())
                ).map(UserMapper::detach)
                        .orElseThrow(() -> new PermissionException("Please enter valid credentials!"));
            } else {
                userEntity.get().setNumber_of_faild_pass_entering(userEntity.get().getNumber_of_faild_pass_entering() + 1);
                userDAO.updateElement(userEntity.get());
                throw new PermissionException("Please enter valid credentials!");
            }
        }
    }
}
