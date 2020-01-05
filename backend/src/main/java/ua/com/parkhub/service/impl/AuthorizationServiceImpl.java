package ua.com.parkhub.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ua.com.parkhub.dto.LoginDTO;
import ua.com.parkhub.exceptions.PermissionException;
import ua.com.parkhub.exceptions.StatusCode;
import ua.com.parkhub.model.UserModel;
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
    public UserModel loginUser(LoginDTO userDTO) {
        Optional<UserModel> userModel = userDAO.findUserByEmail(userDTO.getEmail());
        UserModel user;
        if (userModel.isPresent()) {
            user = userModel.get();
            activateIfPossible(user);
            if (user.getNumberOfFaildPassEntering() >= THREE_TRIES_TO_ENTER) {
                blockIfNeeded(user);
            }
            return checkCredentials(userDTO, user);
        } else {
            throw new PermissionException(StatusCode.NO_ACCOUNT_FOUND);
        }
    }

    private void blockIfNeeded(UserModel user) {
        if (!(blockedUserDAO.isBlocked(user))) {
            blockedUserDAO.blockUser(user);
            throw new PermissionException(StatusCode.ACCOUNT_BLOCKED);
        }
    }

    private void activateIfPossible(UserModel user) {
        if ((blockedUserDAO.isBlocked(user)) && (blockedUserDAO.canActivate(user))) {
            blockedUserDAO.activateUser(user);
            user.setNumberOfFaildPassEntering(0);
            userDAO.updateElement(user);
        }
    }

    private UserModel checkCredentials(LoginDTO user, UserModel userModel) {
        if (passwordEncoder.matches(user.getPassword(), userModel.getPassword())) {
            if (!(blockedUserDAO.isBlocked(userModel))) {
                return userModel;
            } else {
                throw new PermissionException(StatusCode.CANNOT_ACTIVATE);
            }
        } else {
            userModel.setNumberOfFaildPassEntering(userModel.getNumberOfFaildPassEntering() + ONE_FAILD_TRIE_TO_ENTER);
            userDAO.updateElement(userModel);
            if (blockedUserDAO.isBlocked(userModel)) {
                throw new PermissionException(StatusCode.ACCOUNT_BLOCKED);
            }
            throw new PermissionException(StatusCode.INVALID_CREDENTIALS);
        }
    }

}
