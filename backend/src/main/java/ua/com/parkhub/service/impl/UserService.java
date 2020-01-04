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
import ua.com.parkhub.model.UuidTokenType;
import ua.com.parkhub.persistence.entities.UuidToken;
import ua.com.parkhub.persistence.entities.User;
import ua.com.parkhub.persistence.impl.UuidTokenDAO;
import ua.com.parkhub.persistence.impl.UserDAO;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Service
public class UserService {

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

    @Transactional
    public void sendToken(String email, UuidTokenType type) {
        UuidToken token = createToken(email);
        String subject;
        String body;
        switch (type) {
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

    @Transactional
    public void resendToken(String token, UuidTokenType type) {
        String email = uuidTokenDAO.findUuidTokenByToken(token)
                .map(uuidToken -> uuidToken.getUser().getEmail())
                .orElseThrow(() -> new InvalidTokenException("Invalid link"));
        logger.info("Resending email");
        sendToken(email, type);
        logger.info("Email was resend");
    }

    public void verifyEmail(String token) {
        User user = uuidTokenDAO.findUuidTokenByToken(token)
                .map(UuidToken::getUser).orElseThrow(() -> new NotFoundInDataBaseException("Token was not found"));
        user.setRole(signUpService.findUserRole(String.valueOf(RoleDTO.USER)));
        userDAO.updateElement(user);
        logger.info("Email was verified for user with id={}", user.getId());
    }

    @Transactional
    public void resetPassword(String token, String password) {
        User user = uuidTokenDAO
                .findUuidTokenByToken(token)
                .map(uuidToken -> {
                    if (isExpired(uuidToken)) {
                        throw new InvalidTokenException("Token expired!");
                    }
                    return userDAO.findElementById(uuidToken.getUser().getId()).orElseThrow(() ->
                            new InvalidTokenException("Token is not assigned to any user"));
                })
                .orElseThrow(() -> new NotFoundInDataBaseException("Token was not found"));
        user.setPassword(passwordEncoder.encode(password));
        userDAO.updateElement(user);
        logger.info("Password was reset for user with id={}", user.getId());
    }

    private UuidToken createToken(String email) {
        User user = userDAO.findUserByEmail(email)
                .orElseThrow(() -> new EmailException("User with this email does not exist!"));
        UuidToken token = new UuidToken();
        token.setUser(user);
        token.setToken(UUID.randomUUID().toString());
        uuidTokenDAO.addElement(token);
        logger.info("Token={} with expiration date at {} was created for user with id={}",
                token.getToken(), token.getExpirationDate(), user.getId());
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

    public boolean isLinkActive(String token) {
        UuidToken uuidToken = uuidTokenDAO.findUuidTokenByToken(token)
                .orElseThrow(() -> new NotFoundInDataBaseException("Token was not found"));
        return !isExpired(uuidToken);
    }

    private boolean isExpired(UuidToken token) {
        return token.getExpirationDate().isBefore(LocalDateTime.now());
    }

    private String formatExpirationDate(LocalDateTime date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss dd.MM.yyyy");
        return date.format(formatter);
    }
}