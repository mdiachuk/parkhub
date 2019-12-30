package ua.com.parkhub.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ua.com.parkhub.dto.ManagerRegistrationDataDTO;
import ua.com.parkhub.dto.RoleDTO;
import ua.com.parkhub.exceptions.EmailIsUsedException;
import ua.com.parkhub.exceptions.NotFoundInDataBaseException;
import ua.com.parkhub.exceptions.PhoneNumberIsUsedException;
import ua.com.parkhub.persistence.entities.*;
import ua.com.parkhub.persistence.impl.*;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SignUpService {

    private static final long ADMIN_ID = 1;
//    private static final String PENDING_ROLE_NAME = "Pending";
    private static final String MANAGER_REGISTRATION_REQUEST_TICKET_TYPE = "Manager registration request";

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
        if (customer.getId() == null) {
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
                throw new PhoneNumberIsUsedException();
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
            throw new EmailIsUsedException();
        }
        User user = new User();
        user.setCustomer(customer);
        user.setFirstName(manager.getFirstName());
        user.setLastName(manager.getLastName());
        user.setEmail(manager.getEmail());
        user.setPassword(passwordEncoder.encode(manager.getPassword()));
        user.setRole(findUserRole(String.valueOf(RoleDTO.PENDING)));
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
        solvers.add(findAdmin(ADMIN_ID));
        supportTicket.setSolvers(solvers);
        supportTicket.setSupportTicketType(findSupportTicketType(MANAGER_REGISTRATION_REQUEST_TICKET_TYPE));
        logger.info("New support ticket was created");
        return supportTicket;
    }

    private String generateDescription(ManagerRegistrationDataDTO manager) {
        return "Company: <" + manager.getCompanyName() + "> " +
                "USREOU: <" + manager.getUsreouCode() + "> " +
                "Comment: <" + manager.getComment() + ">";
    }

    UserRole findUserRole(String name) {
        return userRoleDAO.findUserRoleByRoleName(name).orElseThrow(() ->
                new NotFoundInDataBaseException("Role was not found by name=" + name));
    }

    private User findAdmin(long id) {
        return userDAO.findElementById(ADMIN_ID).orElseThrow(() -> new
                NotFoundInDataBaseException("Admin was not found by id=" + id));
    }

    private SupportTicketType findSupportTicketType(String type) {
        return supportTicketTypeDAO.findSupportTicketTypeByType(type).orElseThrow(() ->
                new NotFoundInDataBaseException("Support ticket type was not found by type=" + type));
    }


    /**
     * To create new user
     * @param user
     * @return false - if user not create;
     *         true - if user create
     */
    public boolean createUser(User user) {

        if (emptyField(user)){
//            System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!1");
            String haveEmail = userDAO.haveEmail(user.getEmail());
//            System.out.println(haveEmail);
            String havePhoneNumber = userDAO.havePhoneNumber(user.getCustomer().getPhoneNumber());
//            System.out.println(havePhoneNumber);
            String customerId = userDAO.findUserByCustomerId(havePhoneNumber);
//            System.out.println(customerId);
            if (haveEmail.length() != 0) {
                return false;
            } else {
                if (havePhoneNumber.length() != 0) {
                    if (customerId.length() != 0) {
                        return false;
                    } else {
                        return addUser(user);
                    }
                } else  {
                    return addUser(user);
                }
            }
        } else {
            return false;
        }

    }

    /**
     * If some fild is empty, new User can`t create account
     * @param user
     * @return false - if have empty field;
     *         true - if all field not empty
     */
    private boolean emptyField (User user) {
        return (user.getCustomer().getPhoneNumber().length()==0||
                user.getEmail().length()==0||
                user.getFirstName().length()==0||
                user.getPassword().length()==0||
                user.getLastName().length()==0||
                user.getRole().getId()!=1)?false:true;
    }

    private boolean addUser(User user) {
        user.getCustomer().setActive(true);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(findUserRole(String.valueOf(RoleDTO.PENDING)));
        userDAO.addElement(user);
        return true;

    }

}