
package ua.com.parkhub.service.impl;

//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import ua.com.parkhub.dto.LoginDTO;
//import ua.com.parkhub.exceptions.PermissionException;
//import ua.com.parkhub.persistence.entities.User;
//import ua.com.parkhub.persistence.entities.UserRole;
//import ua.com.parkhub.persistence.impl.UserDAO;
//
//import java.time.Duration;
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.ArgumentMatchers.anyString;
//import static org.mockito.Mockito.when;
//
//class AuthorizationServiceImplTest {
//
//    private static final int TIMEOUT = 3000;
//
//    @Mock
//    private UserDAO userDAO;
//    @Mock
//    private PasswordEncoder passwordEncoder; // Is needed after Max`s merge
//
//    private User user;
//    private LoginDTO loginUser;
//    private LoginDTO loginWithWrongCredentials;
//
//    @InjectMocks
//    AuthorizationServiceImpl authService;
//
//    @BeforeEach
//    public void init() {
//        MockitoAnnotations.initMocks(this);
//        loginUser = new LoginDTO();
//        loginUser.setEmail("email");
//        loginUser.setPassword("password");
//        user = new User();
//        user.setId(1L);
//        UserRole role = new UserRole();
//        role.setRoleName("USER");
//        user.setRole(role);
//        user.setFirstName("A");
//        user.setLastName("B");
//        user.setEmail("email");
//        user.setPassword(passwordEncoder.encode("password"));
//        loginWithWrongCredentials = new LoginDTO();
//        loginWithWrongCredentials.setEmail("wrongEmail");
//        loginWithWrongCredentials.setPassword("wrongPassword");
//    }
//
//    @Test
//    public void testLoginUserWithCorrectCredentials() {
//        when(userDAO.findUserByEmail(anyString()))
//                .thenReturn(Optional.of(user));
//        assertTimeout(Duration.ofMillis(TIMEOUT), () -> authService.loginUser(loginUser));
//    }
//
//
//    @Test
//    public void testLoginUserWithIncorrectCredentialsShouldBeFailed() {
//        Optional<User> userOptional = Optional.ofNullable(user);
//        when(userDAO.findUserByEmail(any())).thenReturn(userOptional);
//        Assertions.assertThrows(PermissionException.class, () -> authService.loginUser(loginWithWrongCredentials));
//    }
//
//}
