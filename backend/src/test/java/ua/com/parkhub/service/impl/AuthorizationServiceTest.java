package ua.com.parkhub.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;
import ua.com.parkhub.exceptions.PermissionException;
import ua.com.parkhub.model.UserModel;
import ua.com.parkhub.model.enums.RoleModel;
import ua.com.parkhub.persistence.impl.BlockedUserDAO;
import ua.com.parkhub.persistence.impl.UserDAO;

import java.time.Duration;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTimeout;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

class AuthorizationServiceTest {

    private static final int TIMEOUT = 2000;

    @Mock
    private UserDAO userDAO;

    @Mock
    private BlockedUserDAO blockedUserDAO;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    AuthorizationService authorizationService;

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void test_loginUser_noAccountFound_PermissionException_thrown() {

        UserModel user = new UserModel();
        user.setEmail("email@email.com");
        user.setPassword(passwordEncoder.encode("password"));

        UserModel loginUser = new UserModel();
        loginUser.setEmail("incorrect@email.com");
        loginUser.setPassword(passwordEncoder.encode("password"));

        when(userDAO.findUserByEmail(anyString()))
                .thenReturn(Optional.of(user));

        assertTimeout(Duration.ofMillis(TIMEOUT), () -> {
            assertThrows(PermissionException.class, () -> authorizationService.loginUser(loginUser));
        });
    }

    @Test
    void test_loginUser_INVALID_CREDENTIALS_PermissionException_thrown() {

        UserModel user = new UserModel();
        user.setEmail("email@email.com");
        user.setPassword(passwordEncoder.encode("password"));

        UserModel loginUser = new UserModel();
        loginUser.setEmail("email@email.com");
        loginUser.setPassword(passwordEncoder.encode("wrong_password"));

        when(userDAO.findUserByEmail(anyString()))
                .thenReturn(Optional.of(user));

        assertTimeout(Duration.ofMillis(TIMEOUT), () -> {
            assertThrows(PermissionException.class, () -> authorizationService.loginUser(loginUser));
        });
    }

    @Test
    void test_loginUser_RolePending_PermissionException_thrown() {

        UserModel user = new UserModel();
        user.setEmail("email@email.com");
        user.setPassword(passwordEncoder.encode("password"));
        RoleModel role = RoleModel.PENDING;
        user.setRole(role);

        UserModel loginUser = new UserModel();
        loginUser.setEmail("email@email.com");
        loginUser.setPassword(passwordEncoder.encode("password"));

        when(userDAO.findUserByEmail(anyString()))
                .thenReturn(Optional.of(user));

        assertTimeout(Duration.ofMillis(TIMEOUT), () -> {
            assertThrows(PermissionException.class, () -> authorizationService.loginUser(loginUser));
        });
    }

    @Test
    void test_loginUser_successful() {

        UserModel user = new UserModel();
        user.setEmail("email@email.com");
        user.setPassword(passwordEncoder.encode("password"));

        UserModel loginUser = new UserModel();
        loginUser.setEmail("email@email.com");
        loginUser.setPassword(passwordEncoder.encode("password"));

        when(userDAO.findUserByEmail(anyString()))
                .thenReturn(Optional.of(user));

        assertTimeout(Duration.ofMillis(TIMEOUT), () -> {
            assertThrows(PermissionException.class, () -> authorizationService.loginUser(loginUser));
        });
    }

    @Test
    void test_loginUser_ACCOUNT_BLOCKED_PermissionException_thrown() {

        UserModel user = new UserModel();
        user.setEmail("email@email.com");
        user.setPassword(passwordEncoder.encode("password"));

        UserModel loginUser = new UserModel();
        loginUser.setEmail("email@email.com");
        loginUser.setPassword(passwordEncoder.encode("wrong_password"));

        when(blockedUserDAO.isBlocked(any()))
                .thenReturn(true);

        assertTimeout(Duration.ofMillis(TIMEOUT), () -> {
            assertThrows(PermissionException.class, () -> authorizationService.loginUser(loginUser));
        });
    }


}
