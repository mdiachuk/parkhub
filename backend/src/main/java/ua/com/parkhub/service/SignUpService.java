package ua.com.parkhub.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ua.com.parkhub.dto.ManagerRegistrationDataDTO;
import ua.com.parkhub.exceptions.EmailException;
import ua.com.parkhub.exceptions.NotFoundInDataBaseException;
import ua.com.parkhub.exceptions.PhoneNumberException;
import ua.com.parkhub.persistence.entities.*;
import ua.com.parkhub.persistence.impl.*;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SignUpService {

    private static final long ADMIN_ROLE_ID = 1;
    private static final long PENDING_ROLE_ID = 2;
    private static final long MANAGER_REGISTRATION_REQUEST_TICKET_TYPE_ID = 1;

    private static final Logger logger = LoggerFactory.getLogger(SignUpService.class);

    private final CustomerDAO customerDAO;
    private final UserDAO userDAO;
    private final UserRoleDAO userRoleDAO;
    private final SupportTicketDAO supportTicketDAO;
    private final SupportTicketTypeDAO supportTicketTypeDAO;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public SignUpService(CustomerDAO customerDAO, UserDAO userDAO, UserRoleDAO userRoleDAO,
                         SupportTicketDAO supportTicketDAO, SupportTicketTypeDAO supportTicketTypeDAO,
                         PasswordEncoder passwordEncoder) {
        this.customerDAO = customerDAO;
        this.userDAO = userDAO;
        this.userRoleDAO = userRoleDAO;
        this.supportTicketDAO = supportTicketDAO;
        this.supportTicketTypeDAO = supportTicketTypeDAO;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public void registerManager(ManagerRegistrationDataDTO manager) {
        Customer customer = createCustomer(manager);
        User user = createUser(manager, customer);
        Optional<Long> optionalCustomerId = Optional.ofNullable(customer.getId());
        if (!optionalCustomerId.isPresent()) {
            customerDAO.addElement(customer);
        }
        SupportTicket supportTicket = createSupportTicket(manager, customer);
        supportTicketDAO.addElement(supportTicket);
        customerDAO.updateElement(customer);
        userDAO.addElement(user);
    }

    private Customer createCustomer(ManagerRegistrationDataDTO manager) {
        Optional<Customer> optionalCustomer = customerDAO
                .findCustomerByPhoneNumber(manager.getPhoneNumber());
        if (optionalCustomer.isPresent()) {
            logger.info("Customer with phone number={} was found", manager.getPhoneNumber());
            Optional<User> optionalUser = Optional.ofNullable(optionalCustomer.get().getUser());
            if (optionalUser.isPresent()) {
                throw new PhoneNumberException("Account with this phone number already exists!");
            }
            logger.info("Existing customer was assigned");
            return optionalCustomer.get();
        }
        Customer customer = new Customer();
        customer.setPhoneNumber(manager.getPhoneNumber());
        customer.setActive(true);
        logger.info("New customer was created");
        return customer;
    }

    private User createUser(ManagerRegistrationDataDTO manager, Customer customer) {
        Optional<User> optionalUser = userDAO.findUserByEmail(manager.getEmail());
        if (optionalUser.isPresent()) {
            throw new EmailException("Account with this email already exists!");
        }
        User user = new User();
        user.setCustomer(customer);
        user.setFirstName(manager.getFirstName());
        user.setLastName(manager.getLastName());
        user.setEmail(manager.getEmail());
        user.setPassword(passwordEncoder.encode(manager.getPassword()));
        user.setRole(findUserRole(PENDING_ROLE_ID));
        logger.info("New user was created");
        return user;
    }

    private SupportTicket createSupportTicket(ManagerRegistrationDataDTO manager, Customer customer) {
        SupportTicket supportTicket = new SupportTicket();
        supportTicket.setCustomer(customer);
        List<SupportTicket> supportTickets = Optional
                .ofNullable(customer.getSupportTickets())
                .orElse(new ArrayList<>());
        supportTickets.add(supportTicket);
        customer.setSupportTickets(supportTickets);
        supportTicket.setDescription(generateDescription(manager));
        List<User> solvers = new ArrayList<>();
        solvers.add(findAdmin(ADMIN_ROLE_ID));
        supportTicket.setSolvers(solvers);
        supportTicket.setSupportTicketType(findSupportTicketType
                (MANAGER_REGISTRATION_REQUEST_TICKET_TYPE_ID));
        logger.info("New support ticket was created");
        return supportTicket;
    }

    private String generateDescription(ManagerRegistrationDataDTO manager) {
        return "Company: <" + manager.getCompanyName() + "> " +
                "USREOU: <" + manager.getUsreouCode() + "> " +
                "Comment: <" + manager.getComment() + ">";
    }

    private UserRole findUserRole(long id) {
        return userRoleDAO.findElementById(id).orElseThrow(() ->
                new NotFoundInDataBaseException("Pending role was not found by id=" + id));
    }

    private User findAdmin(long id) {
        return userDAO.findUserByRoleId(id).orElseThrow(() ->
                new NotFoundInDataBaseException("Admin was not found by admin role id=" + id));
    }

    private SupportTicketType findSupportTicketType(long id) {
        return supportTicketTypeDAO.findElementById(id).orElseThrow(() ->
                new NotFoundInDataBaseException("Support ticket type was not found by id=" + id));
    }
}