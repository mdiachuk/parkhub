package ua.com.parkhub.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ua.com.parkhub.dto.LoginDTO;
import ua.com.parkhub.dto.UserDTO;
import ua.com.parkhub.persistence.impl.UserDAO;
import ua.com.parkhub.service.AuthorizationService;

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
       /* return userDAO.findUserByEmail(user.getEmail())
                .filter(
                        userEntity -> passwordEncoder.matches(user.getPassword(), userEntity.getPassword())
                )
                .map(UserMapper::detach)
                .orElseThrow(() -> new PermissionException("Please enter valid credentials!"));*/
       return null;
    }

}
