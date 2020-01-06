package ua.com.parkhub.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ua.com.parkhub.exceptions.EmailException;
import ua.com.parkhub.exceptions.NotFoundInDataBaseException;
import ua.com.parkhub.exceptions.PhoneNumberException;
import ua.com.parkhub.model.*;
import ua.com.parkhub.persistence.impl.*;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.Optional;

@Service
public class SignUpService {

    private static final long ADMIN_ID = 1;

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
    public void registerManager(ManagerRegistrationDataModel manager) {
        CustomerModel customer = createCustomer(manager.getUser().getCustomer());
//        if (customer.getId() == null) {
//            customer = customerDAO.addElement(customer).orElseThrow(() ->
//                    new NotFoundInDataBaseException("Customer not found"));
//        }
        customer = customerDAO.addElement(customer).orElseThrow(() ->
                new NotFoundInDataBaseException("Customer not found"));
        UserModel user = createUser(manager.getUser(), customer);
        userDAO.addElement(user);
        String description = generateDescription(manager.getCompanyName(), manager.getUsreouCode(),
                manager.getComment());
        SupportTicketModel ticket = createTicket(description, customer);
        supportTicketDAO.addElement(ticket);
    }

    @Transactional
    public CustomerModel createCustomer(CustomerModel customer) {
        Optional<CustomerModel> optionalCustomer = customerDAO
                .findCustomerByPhoneNumber(customer.getPhoneNumber());
        if (optionalCustomer.isPresent()) {
            logger.info("Customer with phone number={} was found", customer.getPhoneNumber());
            Optional<UserModel> optionalUser = userDAO
                    .findUserByCustomerId(optionalCustomer.get().getId());
            if (optionalUser.isPresent()) {
                throw new PhoneNumberException("Account with this phone number already exists!");
            }
            logger.info("Existing customer was assigned");
            return optionalCustomer.get();
        }
        customer.setActive(true);
        logger.info("New customer was created");
        return customer;
    }

    @Transactional
    public UserModel createUser(UserModel user, CustomerModel customer) {
        Optional<UserModel> optionalUser = userDAO.findUserByEmail(user.getEmail());
        if (optionalUser.isPresent()) {
            throw new EmailException("Account with this email already exists!");
        }
        user.setCustomer(customer);
        user.setRole(findUserRole(RoleModel.PENDING.getRoleName()));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        logger.info("New user was created");
        return user;
    }

    @Transactional
    public SupportTicketModel createTicket(String description, CustomerModel customer) {
        SupportTicketModel ticket = new SupportTicketModel();
        ticket.setDescription(description);
        ticket.setCustomer(customer);
        ticket.setType(findSupportTicketType(TicketTypeModel.MANAGER_REGISTRATION_REQUEST.getType()));
        ticket.setSolvers(Arrays.asList(findAdmin(1)));
        logger.info("New support ticket was created");
        return ticket;
    }

    private String generateDescription(String companyName, String usreouCode, String comment) {
        return "Company: <" + companyName + "> " +
                "USREOU: <" + usreouCode + "> " +
                "Comment: <" + comment + ">";
    }

    RoleModel findUserRole(String name) {
        return userRoleDAO.findUserRoleByRoleName(name).orElseThrow(() ->
                new NotFoundInDataBaseException("Role was not found by name=" + name));
    }

    TicketTypeModel findSupportTicketType(String type) {
        return supportTicketTypeDAO.findSupportTicketTypeByType(type).orElseThrow(() ->
                new NotFoundInDataBaseException("Support ticket type was not found by type=" + type));
    }

    private UserModel findAdmin(long id) {
        return userDAO.findElementById(ADMIN_ID).orElseThrow(() -> new
                NotFoundInDataBaseException("Admin was not found by id=" + id));
    }

    /**
     * To create new user
     * @param user
     * @return false - if user not create;
     *         true - if user create
     */
//    public boolean createUser(User user) {
//
//        if (emptyField(user)){
////            System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!1");
//            String haveEmail = userDAO.haveEmail(user.getEmail());
////            System.out.println(haveEmail);
//            String havePhoneNumber = userDAO.havePhoneNumber(user.getCustomer().getPhoneNumber());
////            System.out.println(havePhoneNumber);
//            String customerId = userDAO.findUserByCustomerId(havePhoneNumber);
////            System.out.println(customerId);
//            if (haveEmail.length() != 0) {
//                return false;
//            } else {
//                if (havePhoneNumber.length() != 0) {
//                    if (customerId.length() != 0) {
//                        return false;
//                    } else {
//                        return addUser(user);
//                    }
//                } else  {
//                    return addUser(user);
//                }
//            }
//        } else {
//            return false;
//        }
//
//    }
//
//    /**
//     * If some fild is empty, new User can`t create account
//     * @param user
//     * @return false - if have empty field;
//     *         true - if all field not empty
//     */
//    private boolean emptyField (User user) {
//        return (user.getCustomer().getPhoneNumber().length()==0||
//                user.getEmail().length()==0||
//                user.getFirstName().length()==0||
//                user.getPassword().length()==0||
//                user.getLastName().length()==0||
//                user.getRole().getId()!=1)?false:true;
//    }
//
//    private boolean addUser(User user) {
//        user.getCustomer().setActive(true);
//        user.setPassword(passwordEncoder.encode(user.getPassword()));
//        user.setRole(findUserRole(String.valueOf(RoleDTO.PENDING)));
//        userDAO.addElement(user);
//        return true;
//
//    }

}