package ua.com.parkhub.service.impl;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;
import ua.com.parkhub.exceptions.EmailException;
import ua.com.parkhub.exceptions.NotFoundInDataBaseException;
import ua.com.parkhub.exceptions.PhoneNumberException;
import ua.com.parkhub.model.*;
import ua.com.parkhub.model.enums.RoleModel;
import ua.com.parkhub.model.enums.TicketTypeModel;
import ua.com.parkhub.persistence.impl.*;
import ua.com.parkhub.service.IMailService;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
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
    private IMailService mailService;
    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    SignUpService signUpService;

    @BeforeEach
    void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void test_registerManager_pendingRoleNotFound_exceptionThrown() {
        CustomerModel customer = new CustomerModel();
        UserModel user = new UserModel();
        ManagerRegistrationDataModel manager = new ManagerRegistrationDataModel();
        user.setCustomer(customer);
        manager.setUser(user);

        when(userRoleDAO.findUserRoleByRoleName(anyString())).thenReturn(Optional.empty());

        assertTimeout(Duration.ofMillis(TIMEOUT), () -> {
            assertThrows(NotFoundInDataBaseException.class, () -> signUpService.registerManager(manager));
        });
    }

    @Test
    void test_registerManager_adminNotFound_exceptionThrown() {
        CustomerModel customer = new CustomerModel();
        UserModel user = new UserModel();
        ManagerRegistrationDataModel manager = new ManagerRegistrationDataModel();
        user.setCustomer(customer);
        manager.setUser(user);

        when(userDAO.findElementById(anyLong())).thenReturn(Optional.empty());

        assertTimeout(Duration.ofMillis(TIMEOUT), () -> {
            assertThrows(NotFoundInDataBaseException.class, () -> signUpService.registerManager(manager));
        });
    }

    @Test
    void test_registerManager_supportTicketTypeNotFound_exceptionThrown() {
        CustomerModel customer = new CustomerModel();
        UserModel user = new UserModel();
        ManagerRegistrationDataModel manager = new ManagerRegistrationDataModel();
        user.setCustomer(customer);
        manager.setUser(user);

        when(supportTicketTypeDAO.findSupportTicketTypeByType(anyString())).thenReturn(Optional.empty());

        assertTimeout(Duration.ofMillis(TIMEOUT), () -> {
            assertThrows(NotFoundInDataBaseException.class, () -> signUpService.registerManager(manager));
        });
    }

    @Test
    void test_registerManager_phoneNumberIsUsed_exceptionThrown() {
        CustomerModel customer = new CustomerModel();
        UserModel user = new UserModel();
        ManagerRegistrationDataModel manager = new ManagerRegistrationDataModel();
        customer.setId(1L);
        user.setCustomer(customer);
        manager.setUser(user);

        when(customerDAO.findCustomerByPhoneNumber(customer.getPhoneNumber())).thenReturn(Optional.of(customer));
        when(userDAO.findUserByCustomerId(anyLong())).thenReturn(Optional.of(user));

        assertTimeout(Duration.ofMillis(TIMEOUT), () -> {
            assertThrows(PhoneNumberException.class, () -> signUpService.registerManager(manager));
        });
    }

    @Test
    void test_registerManager_phoneNumberIsUsedButNorRegistered_everythingCorrect() {
        CustomerModel customer = new CustomerModel();
        UserModel user = new UserModel();
        ManagerRegistrationDataModel manager = new ManagerRegistrationDataModel();
        user.setId(1L);
        user.setFirstName("Test");
        user.setLastName("Test");
        user.setCustomer(customer);
        manager.setUser(user);
        RoleModel role = RoleModel.PENDING;
        role.setId(1L);
        TicketTypeModel ticketType = TicketTypeModel.MANAGER_REGISTRATION_REQUEST;
        SupportTicketModel ticket = new SupportTicketModel();
        List<UserModel> solvers = new ArrayList<>();
        solvers.add(user);
        ticket.setId(1L);
        ticket.setSolvers(solvers);

        when(customerDAO.findCustomerByPhoneNumber(customer.getPhoneNumber())).thenReturn(Optional.of(customer));
        when(userDAO.addElement(user)).thenReturn(Optional.of(user));
        when(userRoleDAO.findUserRoleByRoleName(anyString())).thenReturn(Optional.of(role));
        when(userDAO.findUsersByRoleId(anyLong())).thenReturn(solvers);
        when(userDAO.findElementById(anyLong())).thenReturn(Optional.of(user));
        when(supportTicketTypeDAO.findSupportTicketTypeByType(anyString())).thenReturn(Optional.of(ticketType));
        when(supportTicketDAO.addElement(any(SupportTicketModel.class))).thenReturn(Optional.of(ticket));

        assertTimeout(Duration.ofMillis(TIMEOUT), () -> signUpService.registerManager(manager));
    }

    @Test
    void test_registerManager_emailIsUsed_exceptionThrown() {
        CustomerModel customer = new CustomerModel();
        UserModel user = new UserModel();
        ManagerRegistrationDataModel manager = new ManagerRegistrationDataModel();
        user.setCustomer(customer);
        manager.setUser(user);

        when(customerDAO.addElement(customer)).thenReturn(Optional.of(customer));
        when(userDAO.findUserByEmail(user.getEmail())).thenReturn(Optional.of(user));

        assertTimeout(Duration.ofMillis(TIMEOUT), () -> {
            assertThrows(EmailException.class, () -> signUpService.registerManager(manager));
        });
    }

    @Test
    void test_registerManager_everythingCorrect() {
        CustomerModel customer = new CustomerModel();
        UserModel user = new UserModel();
        ManagerRegistrationDataModel manager = new ManagerRegistrationDataModel();
        user.setId(1L);
        user.setFirstName("Test");
        user.setLastName("Test");
        user.setCustomer(customer);
        manager.setUser(user);
        RoleModel role = RoleModel.PENDING;
        role.setId(1L);
        TicketTypeModel ticketType = TicketTypeModel.MANAGER_REGISTRATION_REQUEST;
        SupportTicketModel ticket = new SupportTicketModel();
        List<UserModel> solvers = new ArrayList<>();
        solvers.add(user);
        ticket.setId(1L);
        ticket.setSolvers(solvers);

        when(userDAO.addElement(user)).thenReturn(Optional.of(user));
        when(userRoleDAO.findUserRoleByRoleName(anyString())).thenReturn(Optional.of(role));
        when(userDAO.findUsersByRoleId(anyLong())).thenReturn(solvers);
        when(userDAO.findElementById(anyLong())).thenReturn(Optional.of(user));
        when(supportTicketTypeDAO.findSupportTicketTypeByType(anyString())).thenReturn(Optional.of(ticketType));
        when(supportTicketDAO.addElement(any(SupportTicketModel.class))).thenReturn(Optional.of(ticket));

        assertTimeout(Duration.ofMillis(TIMEOUT), () -> signUpService.registerManager(manager));
    }
}
