package ua.com.parkhub.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ua.com.parkhub.dto.LoginDTO;
import ua.com.parkhub.dto.UserDTO;
import ua.com.parkhub.exceptions.PermissionException;
import ua.com.parkhub.exceptions.StatusCode;
import ua.com.parkhub.mappers.UserMapper;
import ua.com.parkhub.persistence.entities.User;
import ua.com.parkhub.persistence.impl.BlockedUserDAO;
import ua.com.parkhub.persistence.impl.UserDAO;
import ua.com.parkhub.service.AuthorizationService;

import java.util.Optional;

@Service
public class AuthorizationServiceImpl implements AuthorizationService {

    private UserDAO userDAO;
    private BlockedUserDAO blockedUserDAO;
    private PasswordEncoder passwordEncoder;

    private final int THREE_TRIES_TO_ENTER = 3;
    private final int ONE_FAILD_TRIE_TO_ENTER = 1;

    public AuthorizationServiceImpl() {
    }

    @Autowired
    public AuthorizationServiceImpl(UserDAO userDAO, BlockedUserDAO blockedUserDAO, PasswordEncoder passwordEncoder) {
        this.userDAO = userDAO;
        this.blockedUserDAO = blockedUserDAO;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public UserDTO loginUser(LoginDTO user) {
        Optional<User> userEntity = userDAO.findUserByEmail(user.getEmail());
        if (userEntity.isPresent()) {
            if (userEntity.get().getNumberOfFaildPassEntering() >= THREE_TRIES_TO_ENTER) {
                blockIfNeeded(userEntity.get());
                activateIfPossible(userEntity.get());
            }
              return checkCredentials(user, userEntity.get());
        } else {
            throw new PermissionException(StatusCode.NO_ACCOUNT_FOUND);
        }
    }

    private void blockIfNeeded(User user){
        if (!(blockedUserDAO.isBlocked(user))) {
            blockedUserDAO.blockUser(user);
            throw new PermissionException(StatusCode.ACCOUNT_BLOCKED);
        }
    }

    private void activateIfPossible(User user){
        if (blockedUserDAO.isBlocked(user) && (blockedUserDAO.canActivate(user))) {
            blockedUserDAO.activateUser(user);
                user.setNumberOfFaildPassEntering(0);
                userDAO.updateElement(user);
        }
    }

    private UserDTO checkCredentials(LoginDTO user, User userEntity) {
        if (passwordEncoder.matches(user.getPassword(), userEntity.getPassword())) {
            if (!(blockedUserDAO.isBlocked(userEntity))) {
                return UserMapper.detach(userEntity);
            } else {
                throw new PermissionException(StatusCode.CANNOT_ACTIVATE);
            }
        } else {
            userEntity.setNumberOfFaildPassEntering(userEntity.getNumberOfFaildPassEntering() + ONE_FAILD_TRIE_TO_ENTER);
            userDAO.updateElement(userEntity);
            if (blockedUserDAO.isBlocked(userEntity)){
                throw new PermissionException(StatusCode.ACCOUNT_BLOCKED);
            }
            throw new PermissionException(StatusCode.INVALID_CREDENTIALS);
        }
    }

}
