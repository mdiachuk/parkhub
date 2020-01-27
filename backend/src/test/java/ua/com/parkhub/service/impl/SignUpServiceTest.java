package ua.com.parkhub.service.impl;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;
import ua.com.parkhub.exceptions.*;
import ua.com.parkhub.model.*;
import ua.com.parkhub.model.enums.RoleModel;
import ua.com.parkhub.model.enums.TicketTypeModel;
import ua.com.parkhub.persistence.entities.Customer;
import ua.com.parkhub.persistence.impl.*;
import ua.com.parkhub.service.IMailService;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

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
    @Mock
    private UserModel userModel;

    @Mock
    private CustomerModel customerModel;

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

    @Test
    public void successfulUserCreationAfterOauth2(){
        RoleModel userRole = RoleModel.USER;
        when(userRoleDAO.findUserRoleByRoleName("USER")).thenReturn(Optional.of(userRole));
        signUpService.createUserAfterSocialAuth(userModel);
        verify(userDAO, times(1)).addElement(userModel);
    }

    @Test
    public void unsuccessfulUserCreationAfterOauth2WhenRoleIsNotFound(){
        assertThrows(NotFoundInDataBaseException.class, () -> {
            signUpService.createUserAfterSocialAuth(userModel);
        },"UserRole (USER) does not exist in db");
    }

    @Test
    public void isCustomerNumberEmpty(){
        CustomerModel customer = new CustomerModel();
        customer.setPhoneNumber("Empty");
        when(userModel.getCustomer()).thenReturn(customer);
        assertTrue(customer.getPhoneNumber().equals("Empty"),"Customer number should be(Empty)");

    }

    @Test
    public void setPhoneNumberForOauth2User(){
        PhoneEmailModel phoneEmailModel = new PhoneEmailModel();
        phoneEmailModel.setPhoneNumber("380665441957");
        phoneEmailModel.setEmail("len@gmail.de");
        when(userModel.getCustomer()).thenReturn(customerModel);
        when(userDAO.findUserByEmail(anyString())).thenReturn(Optional.of(userModel));
        signUpService.setPhoneNumberForAuthUser(phoneEmailModel);
        verify(customerDAO, times(1)).updateElement(customerModel);
    }

    @Test
    public void checkIfUserIsPresent(){
        String email = "dsgk@gmail.com";
        when(userDAO.findUserByEmail(anyString())).thenReturn(Optional.of(userModel));
        assertTrue(signUpService.isUserPresentByEmail(email),"User should be present in db");
    }

    @Test
    void isNumberUnique(){
        String phoneNumber = "380665331958";
        List<CustomerModel> customerModels = new ArrayList<>();
        when(customerDAO.findManyByFieldEqual("phoneNumber",phoneNumber)).thenReturn(customerModels);
        assertTrue(signUpService.isNumberUnique(phoneNumber),"Number should be unique");
    }

    @Test
    void generateTokenForOauthUse(){
        when(userDAO.findUserByEmail(anyString())).thenReturn(Optional.empty());
        assertThrows(NotFoundInDataBaseException.class, () -> {
            signUpService.generateTokenForOauthUser("email");
        },"User should be present in db");
    }


}
