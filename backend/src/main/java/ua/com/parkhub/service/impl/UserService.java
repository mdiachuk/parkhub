package ua.com.parkhub.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.com.parkhub.dto.RoleDTO;
import ua.com.parkhub.exceptions.*;
import ua.com.parkhub.model.UserModel;
import ua.com.parkhub.model.UuidTokenModel;
import ua.com.parkhub.model.enums.RoleModel;
import ua.com.parkhub.model.enums.UuidTokenType;
import ua.com.parkhub.persistence.impl.UserRoleDAO;
import ua.com.parkhub.persistence.impl.UuidTokenDAO;
import ua.com.parkhub.persistence.impl.UserDAO;
import ua.com.parkhub.service.IMailService;
import ua.com.parkhub.service.IUserService;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Service
public class UserService implements IUserService {

    @Value("${fronturl}")
    String url;

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    private static final String TOKEN_WAS_NOT_FOUND = "Token was not found";

    private final UserDAO userDAO;
    private final UuidTokenDAO uuidTokenDAO;
    private final UserRoleDAO userRoleDAO;
    private final IMailService mailService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserDAO userDAO, IMailService mailService,
                       UserRoleDAO userRoleDAO,
                       UuidTokenDAO uuidTokenDAO,
                       PasswordEncoder passwordEncoder) {
        this.userDAO = userDAO;
        this.mailService = mailService;
        this.userRoleDAO = userRoleDAO;
        this.uuidTokenDAO = uuidTokenDAO;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public void sendToken(String email, String type) {
        UuidTokenModel token = createToken(email);
        String[] to = { email };
        String subject;
        String body;
        switch (convertToUuidTokenType(type)) {
            case EMAIL:
                subject = "Verify email";
                body = String.format("<a href=\"%s/verify-email?token=%s\">Verify email address</a> (expires at %s)",
                        url, token.getToken(), formatExpirationDate(token.getExpirationDate()));
                mailService.sendEmail(to, subject, body);
                logger.info("Email for verifying email address was sent");
                break;
            case PASSWORD:
                subject = "Reset password";
                body = String.format("<a href=\"%s/reset-password?token=%s\">Reset password</a> (expires at %s)",
                        url, token.getToken(), formatExpirationDate(token.getExpirationDate()));
                mailService.sendEmail(to, subject, body);
                logger.info("Email for resetting password was sent");
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + type);
        }
    }

    @Override
    @Transactional
    public void resendToken(String token, String type) {
        String email = uuidTokenDAO.findUuidTokenByToken(token)
                .map(uuidToken -> uuidToken.getUser().getEmail())
                .orElseThrow(() -> new InvalidTokenException("Invalid link"));
        logger.info("Resending email");
        sendToken(email, type);
        logger.info("Email was resend");
    }

    @Override
    @Transactional
    public void verifyEmail(String token) {
        UserModel user = uuidTokenDAO.findUuidTokenByToken(token)
                .map(uuidToken -> {
                    uuidToken.setExpirationDate(LocalDateTime.now());
                    uuidTokenDAO.updateElement(uuidToken);
                    return uuidToken.getUser();
                }).orElseThrow(() -> new NotFoundInDataBaseException(TOKEN_WAS_NOT_FOUND));
        user.setRole(findUserRole(String.valueOf(RoleDTO.USER)));
        userDAO.updateElement(user);
        logger.info("Email was verified for user with email={}", user.getEmail());
    }

    @Override
    @Transactional
    public void resetPassword(String token, String password) {
        UserModel user = uuidTokenDAO
                .findUuidTokenByToken(token)
                .map(uuidToken -> {
                    if (isExpired(uuidToken)) {
                        throw new InvalidTokenException("Token expired!");
                    }
                    uuidToken.setExpirationDate(LocalDateTime.now());
                    uuidTokenDAO.updateElement(uuidToken);
                    return userDAO.findUserByEmail(uuidToken.getUser().getEmail()).orElseThrow(() ->
                            new InvalidTokenException("Token is not assigned to any user"));
                })
                .orElseThrow(() -> new NotFoundInDataBaseException(TOKEN_WAS_NOT_FOUND));
        user.setPassword(passwordEncoder.encode(password));
        userDAO.updateElement(user);
        logger.info("Password was reset for user with email={}", user.getEmail());
    }

    private UuidTokenModel createToken(String email) {
        UserModel user = userDAO.findUserByEmail(email)
                .orElseThrow(() -> new EmailException("User with this email does not exist!"));
        UuidTokenModel token = new UuidTokenModel();
        token.setUser(user);
        token.setToken(UUID.randomUUID().toString());
        token.setExpirationDate(LocalDateTime.now().plusMinutes(10));
        uuidTokenDAO.addElement(token);
        logger.info("Token={} with expiration date at {} was created for user with email={}",
                token.getToken(), token.getExpirationDate(), user.getEmail());
        return token;
    }

    @Override
    public boolean isLinkActive(String token) {
        UuidTokenModel uuidToken = uuidTokenDAO.findUuidTokenByToken(token)
                .orElseThrow(() -> new NotFoundInDataBaseException(TOKEN_WAS_NOT_FOUND));
        return !isExpired(uuidToken);
    }

    private boolean isExpired(UuidTokenModel token) {
        return token.getExpirationDate().isBefore(LocalDateTime.now());
    }

    private String formatExpirationDate(LocalDateTime date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss dd.MM.yyyy");
        return date.format(formatter);
    }

    private UuidTokenType convertToUuidTokenType(String type) {
        return UuidTokenType.valueOf(type);
    }

    private RoleModel findUserRole(String name) {
        return userRoleDAO.findUserRoleByRoleName(name).orElseThrow(() ->
                new NotFoundInDataBaseException("Role was not found by name=" + name));
    }

    public UserModel findUserById(long id){
        return userDAO.findElementById(id).orElseThrow(() -> new UserDoesntExistException("User not found",StatusCode.USER_NOT_FOUND));
    }

    @Override
    public void updateUser(long id, UserModel userUp) {
        UserModel userModel = findUserById(id);
        userModel.setFirstName(userUp.getFirstName());
        userModel.setLastName(userUp.getLastName());
        userModel.setEmail(userUp.getEmail());
        userModel.getCustomer().setPhoneNumber(userUp.getCustomer().getPhoneNumber());
        userDAO.updateElement(userModel);
        logger.info("User information was updated");
    }

    @Override
    public void changePassword(long id, String newPassword, UserModel userModel){
        UserModel user = findUserById(id);
        if (validatePassword(userModel.getPassword(), user) & validateNewPassword(newPassword, user)){
            user.setPassword(passwordEncoder.encode(newPassword));
            userDAO.updateElement(user);
            logger.info("Password was updated");
        } else {
            logger.info("Password was not updated");
            throw new PasswordException();
        }
    }
    public boolean validatePassword(String password, UserModel userModel){
        return passwordEncoder.matches(password, userModel.getPassword());
    }
    public boolean validateNewPassword(String newPassword, UserModel userModel){
        return (!("".equals(newPassword)) && (!passwordEncoder.matches(newPassword, userModel.getPassword())));
    }
}
