package ua.com.parkhub.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import ua.com.parkhub.exceptions.EmailException;
import ua.com.parkhub.exceptions.InvalidTokenException;
import ua.com.parkhub.exceptions.NotFoundInDataBaseException;
import ua.com.parkhub.model.enums.RoleModel;
import ua.com.parkhub.model.UserModel;
import ua.com.parkhub.model.UuidTokenModel;
import ua.com.parkhub.model.enums.UuidTokenType;
import ua.com.parkhub.persistence.impl.UserDAO;
import ua.com.parkhub.persistence.impl.UuidTokenDAO;

import javax.mail.internet.MimeMessage;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Optional;

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
    private SignUpService signUpService;
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
        when(userDAO.findUserByEmail(anyString())).thenReturn(Optional.empty());

        assertTimeout(Duration.ofMillis(TIMEOUT), () -> {
            assertThrows(EmailException.class, () -> userService.sendToken("email@test.com", UuidTokenType.EMAIL.getType()));
            assertThrows(EmailException.class, () -> userService.sendToken("email@test.com", UuidTokenType.PASSWORD.getType()));
        });
    }

    @Test
    public void test_sendToken_everythingCorrect() {
        UserModel user = new UserModel();
        MimeMessage mimeMessage = Mockito.mock(MimeMessage.class);

        when(userDAO.findUserByEmail(anyString())).thenReturn(Optional.of(user));
        when(mailSender.createMimeMessage()).thenReturn(mimeMessage);

        assertTimeout(Duration.ofMillis(TIMEOUT), () -> {
            userService.sendToken("email@test.com", UuidTokenType.EMAIL.getType());
            userService.sendToken("email@test.com", UuidTokenType.PASSWORD.getType());
        });
    }

    @Test
    public void test_resendToken_tokenNotFound_notFoundInDataBaseExceptionThrown() {
        assertTimeout(Duration.ofMillis(TIMEOUT), () -> {
            assertThrows(InvalidTokenException.class, () -> userService.resendToken("12345", UuidTokenType.EMAIL.getType()));
            assertThrows(InvalidTokenException.class, () -> userService.resendToken("12345", UuidTokenType.PASSWORD.getType()));
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
        UuidTokenModel token = Mockito.mock(UuidTokenModel.class);
        LocalDateTime expirationDate  = LocalDateTime.now().minusMinutes(100);

        when(uuidTokenDAO.findUuidTokenByToken(anyString())).thenReturn(Optional.of(token));
        when(token.getExpirationDate()).thenReturn(expirationDate);

        assertTimeout(Duration.ofMillis(TIMEOUT), () -> {
            assertFalse(userService.isLinkActive("12345"));
        });
    }

    @Test
    public void test_isLinkActive_everythingCorrect_returnTrue() {
        UuidTokenModel token = Mockito.mock(UuidTokenModel.class);
        LocalDateTime expirationDate  = LocalDateTime.now().plusMinutes(100);

        when(uuidTokenDAO.findUuidTokenByToken(anyString())).thenReturn(Optional.of(token));
        when(token.getExpirationDate()).thenReturn(expirationDate);

        assertTimeout(Duration.ofMillis(TIMEOUT), () -> {
            assertTrue(userService.isLinkActive("12345"));
        });
    }

    @Test
    public void test_resetPassword_tokenExpired_invalidTokenExceptionThrown() {
        UuidTokenModel token = Mockito.mock(UuidTokenModel.class);
        LocalDateTime expirationDate  = LocalDateTime.now().minusMinutes(100);

        when(uuidTokenDAO.findUuidTokenByToken(anyString())).thenReturn(Optional.of(token));
        when(token.getExpirationDate()).thenReturn(expirationDate);

        assertTimeout(Duration.ofMillis(TIMEOUT), () -> {
            assertThrows(InvalidTokenException.class, () -> userService.resetPassword("12345", "qwerty"));
        });
    }

    @Test
    public void test_resetPassword_tokenNotFound_notFoundInDataBaseExceptionThrown() {
        assertTimeout(Duration.ofMillis(TIMEOUT), () -> {
            assertThrows(NotFoundInDataBaseException.class, () -> userService.resetPassword("12345", "qwerty"));
        });
    }

    @Test
    public void test_resetPassword_userNotAssigned_invalidTokenExceptionThrown() {
        UuidTokenModel token = Mockito.mock(UuidTokenModel.class);
        LocalDateTime expirationDate  = LocalDateTime.now().plusMinutes(100);
        UserModel user = Mockito.mock(UserModel.class);

        when(uuidTokenDAO.findUuidTokenByToken(anyString())).thenReturn(Optional.of(token));
        when(token.getExpirationDate()).thenReturn(expirationDate);
        when(token.getUser()).thenReturn(user);
        when(userDAO.findElementById(anyLong())).thenReturn(Optional.empty());

        assertTimeout(Duration.ofMillis(TIMEOUT), () -> {
            assertThrows(InvalidTokenException.class, () -> userService.resetPassword("12345", "qwerty"));
        });
    }

    @Test
    public void test_resetPassword_everythingCorrect() {
        UuidTokenModel token = Mockito.mock(UuidTokenModel.class);
        LocalDateTime expirationDate  = LocalDateTime.now().plusMinutes(100);
        UserModel user = Mockito.mock(UserModel.class);

        when(uuidTokenDAO.findUuidTokenByToken(anyString())).thenReturn(Optional.of(token));
        when(token.getExpirationDate()).thenReturn(expirationDate);
        when(token.getUser()).thenReturn(user);
        when(user.getEmail()).thenReturn("email@test.com");
        when(userDAO.findUserByEmail(anyString())).thenReturn(Optional.of(user));

        assertTimeout(Duration.ofMillis(TIMEOUT), () -> {
            userService.resetPassword("12345", "qwerty");
        });
    }

    @Test
    public void test_verifyEmail_tokenNotFound_notFoundInDataBaseExceptionThrown() {
        assertTimeout(Duration.ofMillis(TIMEOUT), () -> {
            assertThrows(NotFoundInDataBaseException.class, () -> userService.verifyEmail("12345"));
        });
    }

    @Test
    public void test_verifyEmail_everythingCorrect() {
        UuidTokenModel token = Mockito.mock(UuidTokenModel.class);
        UserModel user = Mockito.mock(UserModel.class);
        RoleModel role = RoleModel.USER;

        when(uuidTokenDAO.findUuidTokenByToken(anyString())).thenReturn(Optional.of(token));
        when(token.getUser()).thenReturn(user);
        when(signUpService.findUserRole(anyString())).thenReturn(role);

        assertTimeout(Duration.ofMillis(TIMEOUT), () -> {
            userService.verifyEmail("12345");
        });
    }
}
