package ua.com.parkhub.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.com.parkhub.dto.RoleDTO;
import ua.com.parkhub.exceptions.EmailException;
import ua.com.parkhub.exceptions.InvalidTokenException;
import ua.com.parkhub.exceptions.NotFoundInDataBaseException;
import ua.com.parkhub.exceptions.PasswordException;
import ua.com.parkhub.model.UserModel;
import ua.com.parkhub.model.UuidTokenModel;
import ua.com.parkhub.model.enums.UuidTokenType;
import ua.com.parkhub.persistence.impl.UuidTokenDAO;
import ua.com.parkhub.persistence.impl.UserDAO;
import ua.com.parkhub.service.IUserService;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService implements IUserService {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    private final UserDAO userDAO;
    private final UuidTokenDAO uuidTokenDAO;
    private final SignUpService signUpService;
    private final JavaMailSender mailSender;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserDAO userDAO, JavaMailSender mailSender,
                       SignUpService signUpService,
                       UuidTokenDAO uuidTokenDAO,
                       PasswordEncoder passwordEncoder) {
        this.userDAO = userDAO;
        this.mailSender = mailSender;
        this.signUpService = signUpService;
        this.uuidTokenDAO = uuidTokenDAO;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public void sendToken(String email, String type) {
        UuidTokenModel token = createToken(email);
        String subject;
        String body;
        switch (convertToUuidTokenType(type)) {
            case EMAIL:
                subject = "Verify email";
                body = "<a href=\"http://localhost:4200/verify-email?token=" + token.getToken()
                        + "\">Verify email address</a> (expires at " + formatExpirationDate(token.getExpirationDate()) + ")";
                sendEmail(email, subject, body);
                logger.info("Email for verifying email address was sent to {}", email);
                break;
            case PASSWORD:
                subject = "Reset password";
                body = "<a href=\"http://localhost:4200/reset-password?token=" + token.getToken()
                        + "\">Reset password</a> (expires at " + formatExpirationDate(token.getExpirationDate()) + ")";
                sendEmail(email, subject, body);
                logger.info("Email for resetting password was sent to {}", email);
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
                }).orElseThrow(() -> new NotFoundInDataBaseException("Token was not found"));
        user.setRole(signUpService.findUserRole(String.valueOf(RoleDTO.USER)));
        userDAO.updateElement(user);
        logger.info("Email was verified for user with email={}", user.getEmail());
    }

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
                .orElseThrow(() -> new NotFoundInDataBaseException("Token was not found"));
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

    private void sendEmail(String to, String subject, String body) {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, "utf-8");
        try {
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(body, true);
            mailSender.send(message);
        } catch (MessagingException e) {
            logger.info(e.getMessage());
            throw new EmailException("Error occurred while sending email. Please, try again later");
        }
    }

    @Override
    public boolean isLinkActive(String token) {
        UuidTokenModel uuidToken = uuidTokenDAO.findUuidTokenByToken(token)
                .orElseThrow(() -> new NotFoundInDataBaseException("Token was not found"));
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
            throw new PasswordException();
        }
    }
}
