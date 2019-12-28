package ua.com.parkhub.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import ua.com.parkhub.dto.EmailDTO;
import ua.com.parkhub.dto.PasswordDTO;
import ua.com.parkhub.exceptions.EmailException;
import ua.com.parkhub.exceptions.InvalidTokenException;
import ua.com.parkhub.exceptions.NotFoundInDataBaseException;
import ua.com.parkhub.persistence.entities.User;
import ua.com.parkhub.persistence.entities.UuidToken;
import ua.com.parkhub.persistence.impl.UserDAO;
import ua.com.parkhub.persistence.impl.UuidTokenDAO;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

class UserServiceTest {

    private static final int TIMEOUT = 2000;

    @Mock
    private UserDAO userDAO;
    @Mock
    private UuidTokenDAO uuidTokenDAO;
    @Mock
    private JavaMailSender mailSender;
    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    UserService userService;

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void test_sendToken_userNotFound_emailExceptionThrown() {
        EmailDTO email = new EmailDTO();

        when(userDAO.findUserByEmail(anyString())).thenReturn(Optional.empty());

        assertTimeout(Duration.ofMillis(TIMEOUT), () -> {
            assertThrows(EmailException.class, () -> userService.sendToken(email));
        });
    }

    @Test
    public void test_sendToken_everythingCorrect() {
        EmailDTO email = Mockito.mock(EmailDTO.class);
        User user = new User();

        when(userDAO.findUserByEmail(anyString())).thenReturn(Optional.of(user));
        when(email.getEmail()).thenReturn("email@test.com");

        assertTimeout(Duration.ofMillis(TIMEOUT), () -> {
            userService.sendToken(email);
        });
    }

    @Test
    public void test_isLinkActive_tokenNotFound_notFoundInDataBaseExceptionThrown() {
        when(uuidTokenDAO.findUuidTokenByToken(anyString())).thenReturn(Optional.empty());

        assertTimeout(Duration.ofMillis(TIMEOUT), () -> {
            assertThrows(NotFoundInDataBaseException.class, () -> userService.isLinkActive("12345"));
        });
    }

    @Test
    public void test_isLinkActive_tokenExpired_returnFalse() {
        UuidToken token = Mockito.mock(UuidToken.class);
        LocalDateTime expirationDate  = LocalDateTime.now().minusMinutes(100);

        when(uuidTokenDAO.findUuidTokenByToken(anyString())).thenReturn(Optional.of(token));
        when(token.getExpirationDate()).thenReturn(expirationDate);

        assertTimeout(Duration.ofMillis(TIMEOUT), () -> {
            assertFalse(userService.isLinkActive("12345"));
        });
    }

    @Test
    public void test_isLinkActive_everythingCorrect_returnTrue() {
        UuidToken token = Mockito.mock(UuidToken.class);
        LocalDateTime expirationDate  = LocalDateTime.now().plusMinutes(100);

        when(uuidTokenDAO.findUuidTokenByToken(anyString())).thenReturn(Optional.of(token));
        when(token.getExpirationDate()).thenReturn(expirationDate);

        assertTimeout(Duration.ofMillis(TIMEOUT), () -> {
            assertTrue(userService.isLinkActive("12345"));
        });
    }

    @Test
    public void test_resetPassword_tokenExpired_invalidTokenExceptionThrown() {
        PasswordDTO password = Mockito.mock(PasswordDTO.class);
        UuidToken token = Mockito.mock(UuidToken.class);
        LocalDateTime expirationDate  = LocalDateTime.now().minusMinutes(100);

        when(password.getToken()).thenReturn(UUID.randomUUID().toString());
        when(uuidTokenDAO.findUuidTokenByToken(anyString())).thenReturn(Optional.of(token));
        when(token.getExpirationDate()).thenReturn(expirationDate);

        assertTimeout(Duration.ofMillis(TIMEOUT), () -> {
            assertThrows(InvalidTokenException.class, () -> userService.resetPassword(password));
        });
    }

    @Test
    public void test_resetPassword_tokenNotFound_notFoundInDataBaseExceptionThrown() {
        PasswordDTO password = new PasswordDTO();

        assertTimeout(Duration.ofMillis(TIMEOUT), () -> {
            assertThrows(NotFoundInDataBaseException.class, () -> userService.resetPassword(password));
        });
    }

    @Test
    public void test_resetPassword_userNotAssigned_notFoundInDataBaseExceptionThrown() {
        PasswordDTO password = Mockito.mock(PasswordDTO.class);
        UuidToken token = Mockito.mock(UuidToken.class);
        LocalDateTime expirationDate  = LocalDateTime.now().plusMinutes(100);
        User user = Mockito.mock(User.class);

        when(password.getToken()).thenReturn(UUID.randomUUID().toString());
        when(uuidTokenDAO.findUuidTokenByToken(anyString())).thenReturn(Optional.of(token));
        when(token.getExpirationDate()).thenReturn(expirationDate);
        when(token.getUser()).thenReturn(user);
        when(userDAO.findElementById(anyLong())).thenReturn(Optional.empty());

        assertTimeout(Duration.ofMillis(TIMEOUT), () -> {
            assertThrows(NotFoundInDataBaseException.class, () -> userService.resetPassword(password));
        });
    }

    @Test
    public void test_resetPassword_everythingCorrect() {
        PasswordDTO password = Mockito.mock(PasswordDTO.class);
        UuidToken token = Mockito.mock(UuidToken.class);
        LocalDateTime expirationDate  = LocalDateTime.now().plusMinutes(100);
        User user = Mockito.mock(User.class);

        when(password.getToken()).thenReturn(UUID.randomUUID().toString());
        when(uuidTokenDAO.findUuidTokenByToken(anyString())).thenReturn(Optional.of(token));
        when(token.getExpirationDate()).thenReturn(expirationDate);
        when(token.getUser()).thenReturn(user);
        when(userDAO.findElementById(anyLong())).thenReturn(Optional.of(user));

        assertTimeout(Duration.ofMillis(TIMEOUT), () -> {
            userService.resetPassword(password);
        });
    }
}