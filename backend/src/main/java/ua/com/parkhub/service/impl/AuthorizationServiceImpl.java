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
//    private BlockedUserDAO blockedUserDAO;
    private PasswordEncoder passwordEncoder;

    private int threeTriesToEnter = 3;
    private int oneFaildTrieToEnter;

    public AuthorizationServiceImpl() {
    }

    @Autowired
    public AuthorizationServiceImpl(UserDAO userDAO,
//                                    BlockedUserDAO blockedUserDAO,
                                    PasswordEncoder passwordEncoder) {
        this.userDAO = userDAO;
//        this.blockedUserDAO = blockedUserDAO;
        this.passwordEncoder = passwordEncoder;
    }

//    @Override
//    public UserDTO loginUser(LoginDTO user) {
//        return userDAO.findUserByEmail(user.getEmail())
//                .map(u -> {
//                    if (u.getNumberOfFaildPassEntering() >= 3) {
//                        if (!(blockedUserDAO.isBlocked(u))) {
//                            blockedUserDAO.blockUser(u);
//                            throw new PermissionException(StatusCode.ACCOUNT_BLOCKED);
//                        } else {
//                            if (blockedUserDAO.canActivate(u)) {
//                                blockedUserDAO.activateUser(u);
//                                u.setNumberOfFaildPassEntering(0);
//                                userDAO.updateElement(u);
//                            } else {
//                                throw new PermissionException(StatusCode.CANNOT_ACTIVATE);
//                            }
//                        }
//                    }
//                    return checkCredentials(user, u);
//                })
//                .orElseThrow(() -> new PermissionException(StatusCode.NO_ACCOUNT_FOUND));
//    }
//
//    private UserDTO checkCredentials(LoginDTO user, User userEntity) {
//        if (passwordEncoder.matches(user.getPassword(), userEntity.getPassword())) {
//            if (!(blockedUserDAO.isBlocked(userEntity))) {
//                return UserMapper.detach(userEntity);
//            } else {
//                throw new PermissionException(StatusCode.CANNOT_ACTIVATE);
//            }
//        } else {
//            userEntity.setNumberOfFaildPassEntering(userEntity.getNumberOfFaildPassEntering() + 1);
//            userDAO.updateElement(userEntity);
//            throw new PermissionException(StatusCode.INVALID_CREDENTIALS);
//        }
//    }

    @Override
    public UserDTO loginUser(LoginDTO user) {
//        Optional<User> userEntity = userDAO.findUserByEmail(user.getEmail());
//        if (userEntity.isPresent()) {
//            if (userEntity.get().getNumberOfFaildPassEntering() >= threeTriesToEnter) {
//                if (!(blockedUserDAO.isBlocked(userEntity.get()))) {
//                    blockedUserDAO.blockUser(userEntity.get());
//                    throw new PermissionException(StatusCode.ACCOUNT_BLOCKED);
//                } else {
//                    if (blockedUserDAO.canActivate(userEntity.get())) {
//                        blockedUserDAO.activateUser(userEntity.get());
//                        userEntity.get().setNumberOfFaildPassEntering(0);
//                        userDAO.updateElement(userEntity.get());
//                    }
//                }
//            }
//            if (userEntity.filter(userEnt -> passwordEncoder.matches(user.getPassword(), userEnt.getPassword())).isPresent()) {
//                if (!(blockedUserDAO.isBlocked(userEntity.get()))) {
//                    return userEntity.filter(userEnt -> passwordEncoder.matches(user.getPassword(), userEnt.getPassword())
//                    ).map(UserMapper::detach)
//                            .orElseThrow(() -> new PermissionException(StatusCode.INVALID_CREDENTIALS));
//                } else {
//                    throw new PermissionException(StatusCode.CANNOT_ACTIVATE);
//                }
//            } else {
//                userEntity.get().setNumberOfFaildPassEntering(userEntity.get().getNumberOfFaildPassEntering() + oneFaildTrieToEnter);
//                userDAO.updateElement(userEntity.get());
//                throw new PermissionException(StatusCode.INVALID_CREDENTIALS);
//            }
//        } else {
//            throw new PermissionException(StatusCode.NO_ACCOUNT_FOUND);
//        }

        return null;
    }


}
