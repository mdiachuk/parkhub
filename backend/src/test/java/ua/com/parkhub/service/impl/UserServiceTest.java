package ua.com.parkhub.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;
import ua.com.parkhub.exceptions.*;
import ua.com.parkhub.model.enums.RoleModel;
import ua.com.parkhub.model.UserModel;
import ua.com.parkhub.model.UuidTokenModel;
import ua.com.parkhub.model.enums.UuidTokenType;
import ua.com.parkhub.persistence.impl.UserDAO;
import ua.com.parkhub.persistence.impl.UserRoleDAO;
import ua.com.parkhub.persistence.impl.UuidTokenDAO;
import ua.com.parkhub.service.IMailService;

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
    private UserRoleDAO userRoleDAO;
    @Mock
    private IMailService mailService;
    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void test_sendToken_userNotFound_emailExceptionThrown() {
        when(userDAO.findUserByEmail(anyString())).thenReturn(Optional.empty());

        assertTimeout(Duration.ofMillis(TIMEOUT), () -> {
            assertThrows(EmailException.class, () -> userService.sendToken("email@test.com", UuidTokenType.EMAIL.getType()));
            assertThrows(EmailException.class, () -> userService.sendToken("email@test.com", UuidTokenType.PASSWORD.getType()));
        });
    }

    @Test
    void test_sendToken_everythingCorrect() {
        UserModel user = new UserModel();

        when(userDAO.findUserByEmail(anyString())).thenReturn(Optional.of(user));

        assertTimeout(Duration.ofMillis(TIMEOUT), () -> {
            userService.sendToken("email@test.com", UuidTokenType.EMAIL.getType());
            userService.sendToken("email@test.com", UuidTokenType.PASSWORD.getType());
        });
    }

    @Test
    void test_resendToken_tokenNotFound_notFoundInDataBaseExceptionThrown() {
        assertTimeout(Duration.ofMillis(TIMEOUT), () -> {
            assertThrows(InvalidTokenException.class, () -> userService.resendToken("12345", UuidTokenType.EMAIL.getType()));
            assertThrows(InvalidTokenException.class, () -> userService.resendToken("12345", UuidTokenType.PASSWORD.getType()));
        });
    }

    @Test
    void test_isLinkActive_tokenNotFound_notFoundInDataBaseExceptionThrown() {
        when(uuidTokenDAO.findUuidTokenByToken(anyString())).thenReturn(Optional.empty());

        assertTimeout(Duration.ofMillis(TIMEOUT), () -> {
            assertThrows(NotFoundInDataBaseException.class, () -> userService.isLinkActive("12345"));
        });
    }

    @Test
    void test_isLinkActive_tokenExpired_returnFalse() {
        UuidTokenModel token = new UuidTokenModel();
        token.setExpirationDate(LocalDateTime.now().minusMinutes(100));

        when(uuidTokenDAO.findUuidTokenByToken(anyString())).thenReturn(Optional.of(token));

        assertTimeout(Duration.ofMillis(TIMEOUT), () -> {
            assertFalse(userService.isLinkActive("12345"));
        });
    }

    @Test
    void test_isLinkActive_everythingCorrect_returnTrue() {
        UuidTokenModel token = new UuidTokenModel();
        token.setExpirationDate(LocalDateTime.now().plusMinutes(100));

        when(uuidTokenDAO.findUuidTokenByToken(anyString())).thenReturn(Optional.of(token));

        assertTimeout(Duration.ofMillis(TIMEOUT), () -> {
            assertTrue(userService.isLinkActive("12345"));
        });
    }

    @Test
    void test_resetPassword_tokenExpired_invalidTokenExceptionThrown() {
        UuidTokenModel token = new UuidTokenModel();
        token.setExpirationDate(LocalDateTime.now().minusMinutes(100));

        when(uuidTokenDAO.findUuidTokenByToken(anyString())).thenReturn(Optional.of(token));

        assertTimeout(Duration.ofMillis(TIMEOUT), () -> {
            assertThrows(InvalidTokenException.class, () -> userService.resetPassword("12345", "qwerty"));
        });
    }

    @Test
    void test_resetPassword_tokenNotFound_notFoundInDataBaseExceptionThrown() {
        assertTimeout(Duration.ofMillis(TIMEOUT), () -> {
            assertThrows(NotFoundInDataBaseException.class, () -> userService.resetPassword("12345", "qwerty"));
        });
    }

    @Test
    void test_resetPassword_userNotAssigned_invalidTokenExceptionThrown() {
        UuidTokenModel token = new UuidTokenModel();
        token.setExpirationDate(LocalDateTime.now().plusMinutes(100));
        UserModel user = new UserModel();
        token.setUser(user);

        when(uuidTokenDAO.findUuidTokenByToken(anyString())).thenReturn(Optional.of(token));
        when(userDAO.findElementById(anyLong())).thenReturn(Optional.empty());

        assertTimeout(Duration.ofMillis(TIMEOUT), () -> {
            assertThrows(InvalidTokenException.class, () -> userService.resetPassword("12345", "qwerty"));
        });
    }

    @Test
    void test_resetPassword_everythingCorrect() {
        UuidTokenModel token = new UuidTokenModel();
        token.setExpirationDate(LocalDateTime.now().plusMinutes(100));
        UserModel user = new UserModel();
        user.setEmail("email@test.com");
        token.setUser(user);

        when(uuidTokenDAO.findUuidTokenByToken(anyString())).thenReturn(Optional.of(token));
        when(userDAO.findUserByEmail(anyString())).thenReturn(Optional.of(user));

        assertTimeout(Duration.ofMillis(TIMEOUT), () -> {
            userService.resetPassword("12345", "qwerty");
        });
    }

    @Test
    void test_verifyEmail_tokenNotFound_notFoundInDataBaseExceptionThrown() {
        assertTimeout(Duration.ofMillis(TIMEOUT), () -> {
            assertThrows(NotFoundInDataBaseException.class, () -> userService.verifyEmail("12345"));
        });
    }

    @Test
    void test_verifyEmail_everythingCorrect() {
        UuidTokenModel token = new UuidTokenModel();
        UserModel user = new UserModel();
        token.setUser(user);
        RoleModel role = RoleModel.USER;

        when(uuidTokenDAO.findUuidTokenByToken(anyString())).thenReturn(Optional.of(token));
        when(userRoleDAO.findUserRoleByRoleName(anyString())).thenReturn(Optional.of(role));

        assertTimeout(Duration.ofMillis(TIMEOUT), () -> {
            userService.verifyEmail("12345");
        });
    }


    @Test
    void findUserByIdTest() {
        UserModel userModelTest = new UserModel();
        userModelTest.setFirstName("firstName");
        userModelTest.setEmail("ok@gmail");

        Mockito.when(userDAO.findElementById(1L)).thenReturn(Optional.of(userModelTest));

        assertSame(userModelTest, userService.findUserById(1L));
    }

    @Test
    void negativeResultTest_findUserById() {
        Mockito.when(userDAO.findElementById(1000)).thenReturn(Optional.empty());

        assertThrows(UserDoesntExistException.class, () -> userService.findUserById(1000));
    }

    @Test
    void changePasswordTestNegativeResult() {
        String newPassword = "";
        UserModel userModelTest = new UserModel();
        userModelTest.setId(1L);
        userModelTest.setPassword("$2a$10$ck57rhrpiuJ0etVSuI5QVeYkjbiwLmdnkjkZ25ujcux9ivPUNtMca");
        Mockito.when(userDAO.findElementById(1)).thenReturn(Optional.of(userModelTest));

        assertThrows(PasswordException.class, () -> userService.changePassword(1, newPassword, userModelTest));
    }


    @Test
    void validatePasswordTest() {
        String password = "qwerty";
        UserModel userModelSpy = Mockito.spy(new UserModel());
        Mockito.when(userModelSpy.getPassword()).thenReturn("$2a$10$ck57rhrpiuJ0etVSuI5QVeYkjbiwLmdnkjkZ25ujcux9ivPUNtMca");
        Mockito.when(passwordEncoder.matches(password, userModelSpy.getPassword())).thenReturn(true);

        assertTrue(userService.validatePassword(password, userModelSpy));
    }

    @Test
    void validateNewPasswordTestNegativeResult() {
        UserModel userModelSpy = Mockito.spy(new UserModel());
        Mockito.when(userModelSpy.getPassword()).thenReturn("$2a$10$ck57rhrpiuJ0etVSuI5QVeYkjbiwLmdnkjkZ25ujcux9ivPUNtMca");
        String newPassword = "";

        assertFalse(userService.validateNewPassword(newPassword, userModelSpy));
    }
}
