package ua.com.parkhub.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
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

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Service
public class UserService {

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
        String body = "Reset password: http://localhost:4200/reset-password?token=" + token.getToken()
                + " (valid till " + beautifyExpirationDate(token.getExpirationDate()) + ")";
        sendEmail(email.getEmail(), body);
    }

    @Transactional
    public void resetPassword(PasswordDTO password) {
        User user = uuidTokenDAO
                .findUuidTokenByToken(password.getToken())
                .map(token -> {
                    if (token.getExpirationDate().isBefore(LocalDateTime.now())) {
                        throw new InvalidTokenException("Token expired!");
                    }
                    return userDAO.findElementById(token.getUser().getId()).orElseThrow(() ->
                            new NotFoundInDataBaseException("Token is not assigned to any user"));
                })
                .orElseThrow(() -> new NotFoundInDataBaseException("Token was not found"));
        user.setPassword(passwordEncoder.encode(password.getPassword()));
        userDAO.updateElement(user);
    }

    private UuidToken createToken(String email) {
        User user = userDAO.findUserByEmail(email)
                .orElseThrow(() -> new EmailException("User with this email does not exist!"));
        UuidToken token = new UuidToken();
        token.setUser(user);
        token.setToken(UUID.randomUUID().toString());
        uuidTokenDAO.addElement(token);
        return token;
    }

    private void sendEmail(String to, String subject, String body) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(body);
        mailSender.send(message);
    }

    private String beautifyExpirationDate(LocalDateTime date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss dd.MM.yyyy");
        return date.format(formatter);
    }
}