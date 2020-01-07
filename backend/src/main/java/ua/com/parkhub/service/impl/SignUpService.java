package ua.com.parkhub.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ua.com.parkhub.model.UserModel;
import ua.com.parkhub.persistence.impl.*;

import javax.transaction.Transactional;

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

//    public void registerManager(ManagerRegistrationDataDTO manager) {
//        Customer customer = createCustomer(manager);
//        User user = createUser(manager, customer);
//        if (customer.getId() == null) {
//            customerDAO.addElement(customer);
//        }
//        SupportTicket supportTicket = createSupportTicket(manager, customer);
//        supportTicketDAO.addElement(supportTicket);
//        customerDAO.updateElement(customer);
//        userDAO.addElement(user);
//    }
//
//    private Customer createCustomer(ManagerRegistrationDataDTO manager) {
//        Optional<Customer> optionalCustomer = customerDAO
//                .findCustomerByPhoneNumber(manager.getPhoneNumber());
//        if (optionalCustomer.isPresent()) {
//            logger.info("Customer with phone number={} was found", manager.getPhoneNumber());
//            Optional<User> optionalUser = Optional.ofNullable(optionalCustomer.get().getUser());
//            if (optionalUser.isPresent()) {
//                throw new PhoneNumberIsUsedException();
//            }
//            logger.info("Existing customer was assigned");
//            return optionalCustomer.get();
//        }
//        Customer customer = new Customer();
//        customer.setPhoneNumber(manager.getPhoneNumber());
//        customer.setActive(true);
//        logger.info("New customer was created");
//        return customer;
//    }

//    private User createUser(ManagerRegistrationDataDTO manager, Customer customer) {
//        Optional<User> optionalUser = userDAO.findUserByEmail(manager.getEmail());
//        if (optionalUser.isPresent()) {
//            throw new EmailIsUsedException();
//        }
//        User user = new User();
//        user.setCustomer(customer);
//        user.setFirstName(manager.getFirstName());
//        user.setLastName(manager.getLastName());
//        user.setEmail(manager.getEmail());
//        user.setPassword(passwordEncoder.encode(manager.getPassword()));
//        user.setRole(findUserRole(String.valueOf(RoleDTO.PENDING)));
//        logger.info("New user was created");
//        return user;
//    }

//    private SupportTicket createSupportTicket(ManagerRegistrationDataDTO manager, Customer customer) {
//        SupportTicket supportTicket = new SupportTicket();
//        supportTicket.setCustomer(customer);
//        List<SupportTicket> supportTickets = Optional
//                .ofNullable(customer.getSupportTickets())
//                .orElse(new ArrayList<>());
//        supportTickets.add(supportTicket);
//        customer.setSupportTickets(supportTickets);
//        supportTicket.setDescription(generateDescription(manager));
//        List<User> solvers = new ArrayList<>();
//        solvers.add(findAdmin(ADMIN_ID));
//        supportTicket.setSolvers(solvers);
//        supportTicket.setSupportTicketType(findSupportTicketType(MANAGER_REGISTRATION_REQUEST_TICKET_TYPE));
//        logger.info("New support ticket was created");
//        return supportTicket;
//    }

//    private String generateDescription(ManagerRegistrationDataDTO manager) {
//        return "Company: <" + manager.getCompanyName() + "> " +
//                "USREOU: <" + manager.getUsreouCode() + "> " +
//                "Comment: <" + manager.getComment() + ">";
//    }

//    private UserRole findUserRole(String name) {
//        return userRoleDAO.findUserRoleByRoleName(name).orElseThrow(() ->
//                new NotFoundInDataBaseException("Role was not found by name=" + name));
//    }

//    private User findAdmin(long id) {
//        return userDAO.findElementById(ADMIN_ID).orElseThrow(() -> new
//                NotFoundInDataBaseException("Admin was not found by id=" + id));
//    }
//
//    private SupportTicketTypeModel findSupportTicketType(String type) {
//        return supportTicketTypeDAO.findSupportTicketTypeByType(type).orElseThrow(() ->
//                new NotFoundInDataBaseException("Support ticket type was not found by type=" + type));
//    }


    /**
     * To create new user
     *
     * @param userModel
     * @return false - if user not create;
     * true - if user create
     */
    @Transactional
    public boolean createUser(UserModel userModel) {

        if (emptyField(userModel)) {
            userModel.setPassword(passwordEncoder.encode(userModel.getPassword()));
            userModel.setRole(userRoleDAO.findUserRoleByRoleName(userModel.getRole().getRoleName()).get());
            userDAO.addElement(userModel);
            return true;
        }

        return false;
    }


    private boolean emptyField(UserModel userModel) {

        return userModel.getCustomer().getPhoneNumber().length() != 0 &&
                userModel.getEmail().length() != 0 &&
                userModel.getFirstName().length() != 0 &&
                userModel.getPassword().length() != 0 &&
                userModel.getLastName().length() != 0;
    }



}