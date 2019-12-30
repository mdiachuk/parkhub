package ua.com.parkhub.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.com.parkhub.dto.PasswordDTO;
import ua.com.parkhub.dto.RoleDTO;
import ua.com.parkhub.exceptions.EmailException;
import ua.com.parkhub.exceptions.InvalidTokenException;
import ua.com.parkhub.exceptions.NotFoundInDataBaseException;
import ua.com.parkhub.model.UuidTokenTypeModel;
import ua.com.parkhub.persistence.entities.UuidToken;
import ua.com.parkhub.persistence.entities.User;
import ua.com.parkhub.persistence.entities.UuidTokenType;
import ua.com.parkhub.persistence.impl.UuidTokenDAO;
import ua.com.parkhub.persistence.impl.UserDAO;
import ua.com.parkhub.persistence.impl.UuidTokenTypeDAO;

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
    private final UuidTokenTypeDAO uuidTokenTypeDAO;
    private final SignUpService signUpService;
    private final JavaMailSender mailSender;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserDAO userDAO, JavaMailSender mailSender,
                       SignUpService signUpService,
                       UuidTokenDAO uuidTokenDAO,
                       UuidTokenTypeDAO uuidTokenTypeDAO,
                       PasswordEncoder passwordEncoder) {
        this.userDAO = userDAO;
        this.mailSender = mailSender;
        this.signUpService = signUpService;
        this.uuidTokenDAO = uuidTokenDAO;
        this.uuidTokenTypeDAO = uuidTokenTypeDAO;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public void sendToken(String email, UuidTokenTypeModel type) {
        UuidToken token = createToken(email, type);
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
            default:
                throw new IllegalStateException("Unexpected value: " + type);
        }
    }

    @Transactional
    public void resendToken(String token, UuidTokenTypeModel type) {
        String email = uuidTokenDAO.findUuidTokenByToken(token)
                .map(uuidToken -> uuidToken.getUser().getEmail())
                .orElseThrow(() -> new InvalidTokenException("Invalid link"));
        logger.info("Resending email");
        sendToken(email, type);
        logger.info("Email was resend");
    }

    public boolean isLinkActive(String token) {
        UuidToken uuidToken = uuidTokenDAO.findUuidTokenByToken(token)
                .orElseThrow(() -> new NotFoundInDataBaseException("Token was not found"));
        return !isExpired(uuidToken);
    }

    @Transactional
    public void resetPassword(PasswordDTO password) {
        User user = uuidTokenDAO
                .findUuidTokenByToken(password.getToken())
                .map(token -> {
                    if (isExpired(token)) {
                        throw new InvalidTokenException("Token expired!");
                    }
                    return userDAO.findElementById(token.getUser().getId()).orElseThrow(() ->
                            new InvalidTokenException("Token is not assigned to any user"));
                })
                .orElseThrow(() -> new NotFoundInDataBaseException("Token was not found"));
        user.setPassword(passwordEncoder.encode(password.getPassword()));
        userDAO.updateElement(user);
        logger.info("Password was reset for user with id={}", user.getId());
    }

    private UuidToken createToken(String email, UuidTokenTypeModel type) {
        User user = userDAO.findUserByEmail(email)
                .orElseThrow(() -> new EmailException("User with this email does not exist!"));
        UuidToken token = new UuidToken();
        token.setUser(user);
        token.setToken(UUID.randomUUID().toString());
        token.setUuidTokenType(findUuidTokenType(String.valueOf(type)));
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

    private UuidTokenType findUuidTokenType(String type) {
        return uuidTokenTypeDAO.findUuidTokenTypeByType(type).orElseThrow(() ->
                new IllegalArgumentException("UUID token type was not found by type=" + type));
    }
}