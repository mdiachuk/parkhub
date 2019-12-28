package ua.com.parkhub.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.com.parkhub.dto.EmailDTO;
import ua.com.parkhub.dto.PasswordDTO;
import ua.com.parkhub.exceptions.EmailException;
import ua.com.parkhub.exceptions.InvalidTokenException;
import ua.com.parkhub.exceptions.NotFoundInDataBaseException;
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
    private final JavaMailSender mailSender;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserDAO userDAO, JavaMailSender mailSender,
                                UuidTokenDAO uuidTokenDAO,
                                PasswordEncoder passwordEncoder) {
        this.userDAO = userDAO;
        this.mailSender = mailSender;
        this.uuidTokenDAO = uuidTokenDAO;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public void sendToken(EmailDTO email) {
        UuidToken token = createToken(email.getEmail());
        String to = email.getEmail();
        String subject = "Reset password";
        String body = "<a href=\"http://localhost:4200/reset-password?token=" + token.getToken()
                + "\">Reset password</a> (expires at " + formatExpirationDate(token.getExpirationDate()) + ")";
        sendEmail(to, subject, body);
        logger.info("Email for password resetting was sent to {}", to);
    }

    @Transactional
    public void resendTokenForResettingPassword(String token) {
        uuidTokenDAO.findUuidTokenByToken(token)
                .ifPresent(uuidToken -> {
                    EmailDTO email = new EmailDTO();
                    email.setEmail(uuidToken.getUser().getEmail());
                    sendToken(email);
                });
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
                            new NotFoundInDataBaseException("Token is not assigned to any user"));
                })
                .orElseThrow(() -> new NotFoundInDataBaseException("Token was not found"));
        user.setPassword(passwordEncoder.encode(password.getPassword()));
        userDAO.updateElement(user);
        logger.info("Password was reset");
    }

    private UuidToken createToken(String email) {
        User user = userDAO.findUserByEmail(email)
                .orElseThrow(() -> new EmailException("User with this email does not exist!"));
        UuidToken token = new UuidToken();
        token.setUser(user);
        token.setToken(UUID.randomUUID().toString());
        uuidTokenDAO.addElement(token);
        logger.info("Token {} with expiration date at {} was created for user with email={}",
                token.getToken(), token.getExpirationDate(), email);
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

    private String formatExpirationDate(LocalDateTime date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss dd.MM.yyyy");
        return date.format(formatter);
    }

    private boolean isExpired(UuidToken token) {
        return token.getExpirationDate().isBefore(LocalDateTime.now());
    }
}