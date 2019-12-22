package ua.com.parkhub.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ua.com.parkhub.dto.ManagerDTO;
import ua.com.parkhub.exceptions.EmailIsUsedException;
import ua.com.parkhub.exceptions.NotFoundInDataBaseException;
import ua.com.parkhub.exceptions.PhoneNumberIsUsedException;
import ua.com.parkhub.persistence.entities.Customer;
import ua.com.parkhub.persistence.entities.SupportTicket;
import ua.com.parkhub.persistence.entities.User;
import ua.com.parkhub.persistence.impl.*;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SignUpService {

    private static final long ADMIN_ID = 1;
    private static final String PENDING_ROLE_NAME = "Pending";
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
    public void registerManager(ManagerDTO managerDto) {
        Customer customer = createCustomer(managerDto);
        User user = createUser(managerDto, customer);
        if (customer.getId() == null) {
            customerDAO.addElement(customer);
        }
        SupportTicket supportTicket = createSupportTicket(managerDto, customer);
        supportTicketDAO.addElement(supportTicket);
        customerDAO.updateElement(customer);
        userDAO.addElement(user);
    }

    private Customer createCustomer(ManagerDTO managerDTO) {
        Optional<Customer> optionalCustomer = customerDAO
                .findCustomerByPhoneNumber(managerDTO.getPhoneNumber());
        if (optionalCustomer.isPresent()) {
            logger.info("Customer with phone number={} was found", managerDTO.getPhoneNumber());
            Optional<User> optionalUser = Optional.ofNullable(optionalCustomer.get().getUser());
            if (optionalUser.isPresent()) {
                throw new PhoneNumberIsUsedException();
            }
            logger.info("Existing customer was assigned");
            return optionalCustomer.get();
        }
        Customer customer = new Customer();
        customer.setPhoneNumber(managerDTO.getPhoneNumber());
        customer.setActive(true);
        logger.info("New customer was created");
        return customer;
    }

    private User createUser(ManagerDTO managerDTO, Customer customer) {
        Optional<User> optionalUser = userDAO.findUserByEmail(managerDTO.getEmail());
        if (optionalUser.isPresent()) {
            throw new EmailIsUsedException();
        }
        User user = new User();
        user.setCustomer(customer);
        user.setFirstName(managerDTO.getFirstName());
        user.setLastName(managerDTO.getLastName());
        user.setEmail(managerDTO.getEmail());
        user.setPassword(passwordEncoder.encode(managerDTO.getPassword()));
        user.setRole(userRoleDAO
                .findUserRoleByRoleName(PENDING_ROLE_NAME)
                .orElseThrow(() -> new NotFoundInDataBaseException("Role was not found by name="
                        + PENDING_ROLE_NAME)));
        logger.info("New user created");
        return user;
    }

    private SupportTicket createSupportTicket(ManagerDTO managerDTO, Customer customer) {
        SupportTicket supportTicket = new SupportTicket();
        supportTicket.setCustomer(customer);
        List<SupportTicket> supportTickets = Optional
                .ofNullable(customer.getSupportTickets())
                .orElse(new ArrayList<>());
        supportTickets.add(supportTicket);
        customer.setSupportTickets(supportTickets);
        supportTicket.setDescription(generateDescription(managerDTO));
        List<User> solvers = new ArrayList<>();
        solvers.add(userDAO
                .findElementById(ADMIN_ID)
                .orElseThrow(() -> new NotFoundInDataBaseException("Admin was not found by id=" + ADMIN_ID)));
        supportTicket.setSolvers(solvers);
        supportTicket.setSupportTicketType(supportTicketTypeDAO
                .findSupportTicketTypeByType(MANAGER_REGISTRATION_REQUEST_TICKET_TYPE)
                .orElseThrow(() -> new NotFoundInDataBaseException("Support ticket type was not found by type="
                        + MANAGER_REGISTRATION_REQUEST_TICKET_TYPE)));
        logger.info("New support ticket was created");
        return supportTicket;
    }

    private String generateDescription(ManagerDTO managerDTO) {
        return "Company: <" + managerDTO.getCompanyName() + "> " +
                "USREOU: <" + managerDTO.getUsreouCode() + "> " +
                "Comment: <" + managerDTO.getComment() + ">";
    }
}