package ua.com.parkhub.service;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;
import ua.com.parkhub.exceptions.EmailException;
import ua.com.parkhub.exceptions.NotFoundInDataBaseException;
import ua.com.parkhub.exceptions.PhoneNumberException;
import ua.com.parkhub.model.*;
import ua.com.parkhub.persistence.impl.*;
import ua.com.parkhub.service.impl.SignUpService;

import java.time.Duration;
import java.util.Arrays;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;

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

    @Test
    public void test_registerManager_pendingRoleNotFound_exceptionThrown() {
        ManagerRegistrationDataModel manager = Mockito.mock(ManagerRegistrationDataModel.class);
        CustomerModel customer = new CustomerModel();
        UserModel user = Mockito.mock(UserModel.class);

        when(manager.getUser()).thenReturn(user);
        when(user.getCustomer()).thenReturn(customer);
        when(userRoleDAO.findUserRoleByRoleName(anyString())).thenReturn(Optional.empty());

        assertTimeout(Duration.ofMillis(TIMEOUT), () -> {
            assertThrows(NotFoundInDataBaseException.class, () -> signUpService.registerManager(manager));
        });
    }

    @Test
    public void test_registerManager_adminNotFound_exceptionThrown() {
        ManagerRegistrationDataModel manager = Mockito.mock(ManagerRegistrationDataModel.class);
        CustomerModel customer = new CustomerModel();
        UserModel user = Mockito.mock(UserModel.class);

        when(manager.getUser()).thenReturn(user);
        when(user.getCustomer()).thenReturn(customer);
        when(userDAO.findElementById(anyLong())).thenReturn(Optional.empty());

        assertTimeout(Duration.ofMillis(TIMEOUT), () -> {
            assertThrows(NotFoundInDataBaseException.class, () -> signUpService.registerManager(manager));
        });
    }

    @Test
    public void test_registerManager_supportTicketTypeNotFound_exceptionThrown() {
        ManagerRegistrationDataModel manager = Mockito.mock(ManagerRegistrationDataModel.class);
        CustomerModel customer = new CustomerModel();
        UserModel user = Mockito.mock(UserModel.class);

        when(manager.getUser()).thenReturn(user);
        when(user.getCustomer()).thenReturn(customer);
        when(supportTicketTypeDAO.findSupportTicketTypeByType(anyString())).thenReturn(Optional.empty());

        assertTimeout(Duration.ofMillis(TIMEOUT), () -> {
            assertThrows(NotFoundInDataBaseException.class, () -> signUpService.registerManager(manager));
        });
    }

    @Test
    public void test_registerManager_phoneNumberIsUsed_exceptionThrown() {
        ManagerRegistrationDataModel manager = Mockito.mock(ManagerRegistrationDataModel.class);
        CustomerModel customer = Mockito.mock(CustomerModel.class);
        UserModel user = Mockito.mock(UserModel.class);

        when(manager.getUser()).thenReturn(user);
        when(user.getCustomer()).thenReturn(customer);
        when(customerDAO.findCustomerByPhoneNumber(customer.getPhoneNumber())).thenReturn(Optional.of(customer));
        when(userDAO.findUserByCustomerId(anyLong())).thenReturn(Optional.of(user));

        assertTimeout(Duration.ofMillis(TIMEOUT), () -> {
            assertThrows(PhoneNumberException.class, () -> signUpService.registerManager(manager));
        });
    }

    @Test
    public void test_registerManager_phoneNumberIsUsedButNorRegistered_everythingCorrect() {
        ManagerRegistrationDataModel manager = Mockito.mock(ManagerRegistrationDataModel.class);
        CustomerModel customer = new CustomerModel();
        UserModel user = Mockito.mock(UserModel.class);
        RoleModel role = RoleModel.PENDING;
        role.setId((long) 1);
        TicketTypeModel ticketType = TicketTypeModel.MANAGER_REGISTRATION_REQUEST;

        when(manager.getUser()).thenReturn(user);
        when(user.getCustomer()).thenReturn(customer);
        when(customerDAO.findCustomerByPhoneNumber(customer.getPhoneNumber())).thenReturn(Optional.of(customer));
        when(customerDAO.addElement(customer)).thenReturn(Optional.of(customer));
        when(userRoleDAO.findUserRoleByRoleName(anyString())).thenReturn(Optional.of(role));
        when(userDAO.findUsersByRoleId(anyLong())).thenReturn(Arrays.asList(user));
        when(userDAO.findElementById(anyLong())).thenReturn(Optional.of(user));
        when(supportTicketTypeDAO.findSupportTicketTypeByType(anyString())).thenReturn(Optional.of(ticketType));

        assertTimeout(Duration.ofMillis(TIMEOUT), () -> signUpService.registerManager(manager));
    }

    @Test
    public void test_registerManager_emailIsUsed_exceptionThrown() {
        ManagerRegistrationDataModel manager = Mockito.mock(ManagerRegistrationDataModel.class);
        CustomerModel customer = new CustomerModel();
        UserModel user = Mockito.mock(UserModel.class);

        when(manager.getUser()).thenReturn(user);
        when(user.getCustomer()).thenReturn(customer);
        when(customerDAO.addElement(customer)).thenReturn(Optional.of(customer));
        when(userDAO.findUserByEmail(user.getEmail())).thenReturn(Optional.of(user));

        assertTimeout(Duration.ofMillis(TIMEOUT), () -> {
            assertThrows(EmailException.class, () -> signUpService.registerManager(manager));
        });
    }

    @Test
    public void test_registerManager_everythingCorrect() {
        ManagerRegistrationDataModel manager = Mockito.mock(ManagerRegistrationDataModel.class);
        CustomerModel customer = new CustomerModel();
        UserModel user = Mockito.mock(UserModel.class);
        RoleModel role = RoleModel.PENDING;
        role.setId((long) 1);
        TicketTypeModel ticketType = TicketTypeModel.MANAGER_REGISTRATION_REQUEST;

        when(manager.getUser()).thenReturn(user);
        when(user.getCustomer()).thenReturn(customer);
        when(customerDAO.addElement(customer)).thenReturn(Optional.of(customer));
        when(userRoleDAO.findUserRoleByRoleName(anyString())).thenReturn(Optional.of(role));
        when(userDAO.findUsersByRoleId(anyLong())).thenReturn(Arrays.asList(user));
        when(userDAO.findElementById(anyLong())).thenReturn(Optional.of(user));
        when(supportTicketTypeDAO.findSupportTicketTypeByType(anyString())).thenReturn(Optional.of(ticketType));

        assertTimeout(Duration.ofMillis(TIMEOUT), () -> signUpService.registerManager(manager));
    }
}