package ua.com.parkhub.service;


import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;
import ua.com.parkhub.persistence.impl.*;
import ua.com.parkhub.service.impl.SignUpService;

class SignUpServiceTest {

    private static final int TIMEOUT = 2000;

    @Mock
    private CustomerDAO customerDAO;
    @Mock
    private UserDAO userDAO;
    @Mock
    private UserRoleDAO userRoleDAO;
    @Mock
    private SupportTicketDAO supportTicketDAO;
    @Mock
    private SupportTicketTypeDAO supportTicketTypeDAO;
    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    SignUpService signUpService;

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
    }
/*
    @Test
    public void test_registerManager_pendingRoleNotFound_exceptionThrown() {
        ManagerRegistrationDataDTO manager = new ManagerRegistrationDataDTO();

        when(userRoleDAO.findUserRoleByRoleName(anyString()))
                .thenReturn(Optional.empty());

        assertTimeout(Duration.ofMillis(TIMEOUT), () -> {
            assertThrows(NotFoundInDataBaseException.class, () -> signUpService.registerManager(manager));
        });
    }

    @Test
    public void test_registerManager_adminNotFound_exceptionThrown() {
        ManagerRegistrationDataDTO manager = new ManagerRegistrationDataDTO();

        when(userDAO.findElementById(anyLong()))
                .thenReturn(Optional.empty());

        assertTimeout(Duration.ofMillis(TIMEOUT), () -> {
            assertThrows(NotFoundInDataBaseException.class, () -> signUpService.registerManager(manager));
        });
    }

    @Test
    public void test_registerManager_supportTicketTypeNotFound_exceptionThrown() {
        ManagerRegistrationDataDTO manager = new ManagerRegistrationDataDTO();

        when(supportTicketTypeDAO.findSupportTicketTypeByType(anyString()))
                .thenReturn(Optional.empty());

        assertTimeout(Duration.ofMillis(TIMEOUT), () -> {
            assertThrows(NotFoundInDataBaseException.class, () -> signUpService.registerManager(manager));
        });
    }

    @Test
    public void test_registerManager_phoneNumberIsUsed_exceptionThrown() {
        ManagerRegistrationDataDTO manager = new ManagerRegistrationDataDTO();
        Customer customer = Mockito.mock(Customer.class);
        User user = new User();

        when(customerDAO.findCustomerByPhoneNumber(manager.getPhoneNumber()))
                .thenReturn(Optional.of(customer));
        when(Optional.of(customer).get().getUser())
                .thenReturn(user);

        assertTimeout(Duration.ofMillis(TIMEOUT), () -> {
            assertThrows(PhoneNumberIsUsedException.class, () -> signUpService.registerManager(manager));
        });
    }

    @Test
    public void test_registerManager_phoneNumberIsUsedButNorRegistered_everythingCorrect() {
        ManagerRegistrationDataDTO manager = new ManagerRegistrationDataDTO();
        Customer customer = new Customer();
        User user = new User();
        UserRole userRole = new UserRole();
        SupportTicketType supportTicketType = new SupportTicketType();

        when(customerDAO.findCustomerByPhoneNumber(manager.getPhoneNumber()))
                .thenReturn(Optional.of(customer));
        when(userRoleDAO.findUserRoleByRoleName(anyString()))
                .thenReturn(Optional.of(userRole));
        when(userDAO.findElementById(anyLong()))
                .thenReturn(Optional.of(user));
        when(supportTicketTypeDAO.findSupportTicketTypeByType(anyString()))
                .thenReturn(Optional.of(supportTicketType));

        assertTimeout(Duration.ofMillis(TIMEOUT), () -> signUpService.registerManager(manager));
    }

    @Test
    public void test_registerManager_emailIsUsed_exceptionThrown() {
        ManagerRegistrationDataDTO manager = new ManagerRegistrationDataDTO();
        User user = new User();

        when(userDAO.findUserByEmail(manager.getPhoneNumber()))
                .thenReturn(Optional.of(user));

        assertTimeout(Duration.ofMillis(TIMEOUT), () -> {
            assertThrows(EmailIsUsedException.class, () -> signUpService.registerManager(manager));
        });
    }

    @Test
    public void test_registerManager_everythingCorrect() {
        ManagerRegistrationDataDTO manager = new ManagerRegistrationDataDTO();
        User user = new User();
        UserRole userRole = new UserRole();
        SupportTicketType supportTicketType = new SupportTicketType();

        when(userRoleDAO.findUserRoleByRoleName(anyString()))
                .thenReturn(Optional.of(userRole));
        when(userDAO.findElementById(anyLong()))
                .thenReturn(Optional.of(user));
        when(supportTicketTypeDAO.findSupportTicketTypeByType(anyString()))
                .thenReturn(Optional.of(supportTicketType));

        assertTimeout(Duration.ofMillis(TIMEOUT), () -> signUpService.registerManager(manager));
    }*/
}