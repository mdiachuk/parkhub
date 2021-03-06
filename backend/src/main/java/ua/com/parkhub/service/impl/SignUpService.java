package ua.com.parkhub.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ua.com.parkhub.exceptions.EmailException;
import ua.com.parkhub.exceptions.NotFoundInDataBaseException;
import ua.com.parkhub.exceptions.PhoneNumberException;
import ua.com.parkhub.model.enums.RoleModel;
import ua.com.parkhub.model.enums.TicketTypeModel;
import ua.com.parkhub.persistence.impl.*;
import ua.com.parkhub.model.*;
import ua.com.parkhub.service.IMailService;
import ua.com.parkhub.security.JwtUtil;
import ua.com.parkhub.service.ISignUpService;
import ua.com.parkhub.values.Constants;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Service
public class SignUpService implements ISignUpService {

    @Value("${fronturl}")
    private String url;

    private static final Logger logger = Logger.getLogger(SignUpService.class.getName());

    private final CustomerDAO customerDAO;
    private final UserDAO userDAO;
    private final UserRoleDAO userRoleDAO;
    private final SupportTicketDAO supportTicketDAO;
    private final SupportTicketTypeDAO supportTicketTypeDAO;
    private final IMailService mailService;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    @Autowired
    public SignUpService(CustomerDAO customerDAO, UserDAO userDAO, UserRoleDAO userRoleDAO,
                         SupportTicketDAO supportTicketDAO, SupportTicketTypeDAO supportTicketTypeDAO,
                         IMailService mailService, PasswordEncoder passwordEncoder,
                         JwtUtil jwtUtil) {
        this.customerDAO = customerDAO;
        this.userDAO = userDAO;
        this.userRoleDAO = userRoleDAO;
        this.supportTicketDAO = supportTicketDAO;
        this.supportTicketTypeDAO = supportTicketTypeDAO;
        this.mailService = mailService;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    @Transactional
    @Override
    public void registerUser(UserModel user) {
        userDAO.addElement(createUser(user));
    }

    @Transactional
    @Override
    public void registerManager(ManagerRegistrationDataModel manager) {
        UserModel user =  userDAO.addElement(createUser(manager.getUser())).orElseThrow(() ->
                new NotFoundInDataBaseException("User not found"));
        String description = generateDescription(user.getId(), manager.getCompanyName(),
                manager.getUsreouCode(), manager.getComment());
        SupportTicketModel ticket = createTicket(description, user.getCustomer());
        ticket = supportTicketDAO.addElement(ticket).orElseThrow(() ->
                new NotFoundInDataBaseException("Ticket not found"));
        sendNotification(getEmails(ticket.getSolvers()), user.getFirstName(),
                user.getLastName(), ticket.getId());
    }

    @Transactional
    @Override
    public CustomerModel createCustomer(CustomerModel customer) {
        return customerDAO.findCustomerByPhoneNumber(customer.getPhoneNumber()).map(existingCustomer -> {
            logger.info(String.format("Customer with phone number=%s was found", customer.getPhoneNumber()));
            Optional<UserModel> optionalUser = userDAO.findUserByCustomerId(existingCustomer.getId());
            if (optionalUser.isPresent()) {
                throw new PhoneNumberException("Account with this phone number already exists!");
            }
            logger.info("Existing customer was assigned");
            return existingCustomer;
        }).orElseGet(() -> {
            customer.setActive(true);
            logger.info("New customer was created");
            return customer;
        });
    }

    @Transactional
    @Override
    public UserModel createUser(UserModel user) {
        CustomerModel customer = createCustomer(user.getCustomer());
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
    @Override
    public SupportTicketModel createTicket(String description, CustomerModel customer) {
        SupportTicketModel ticket = new SupportTicketModel();
        ticket.setDescription(description);
        ticket.setCustomer(customer);
        ticket.setType(findSupportTicketType(TicketTypeModel.MANAGER_REGISTRATION_REQUEST.getValue()));
        ticket.setSolvers(findSolvers(RoleModel.ADMIN.getRoleName()));
        logger.info("New support ticket was created");
        return ticket;
    }

    private void sendNotification(String[] emails, String firstName, String lastName, long ticketId) {
        String subject = "New registration request";
        String body = String.format("New parking owner registration request was created by %s %s. " +
                "<a href=\"%s/admin/%d\">See details</a>.", firstName, lastName, url, ticketId);
        mailService.sendEmail(emails, subject, body);
        logger.info("Email notification for new parking owner registration request was sent to admins");
    }

    private String[] getEmails(List<UserModel> solvers) {
        return solvers.stream().map(UserModel::getEmail).toArray(String[]::new);
    }

    private String generateDescription(long id, String companyName, String usreouCode, String comment) {
        return String.format("ID: %d Company: \"%s\" USREOU code: %s Comment: \"%s\"", id, companyName,
                usreouCode, comment);
    }

    private RoleModel findUserRole(String name) {
        return userRoleDAO.findUserRoleByRoleName(name).orElseThrow(() ->
                new NotFoundInDataBaseException("Role was not found by name=" + name));
    }

    private TicketTypeModel findSupportTicketType(String type) {
        return supportTicketTypeDAO.findSupportTicketTypeByType(type).orElseThrow(() ->
                new NotFoundInDataBaseException("Support ticket type was not found by type=" + type));
    }

    private List<UserModel> findSolvers(String role) {
        List<UserModel> solvers = userDAO.findUsersByRoleId(findUserRole(role).getId());
        if (solvers.isEmpty()) {
            throw new NotFoundInDataBaseException("Solvers were not found by role=" + role);
        }
        return solvers;
    }

    @Override
    public boolean isUserPresentByEmail(String email) {
        return userDAO.findUserByEmail(email).isPresent();
    }

    @Override
    public void setPhoneNumberForAuthUser(PhoneEmailModel phoneEmailModel) {
        logger.info("Setting phone number for Oauth2 user");
        UserModel userModel = userDAO.findUserByEmail(phoneEmailModel.getEmail()).orElseThrow(() ->
                new NotFoundInDataBaseException(Constants.USERNOTFOUND));
        CustomerModel customerModel = userModel.getCustomer();
        customerModel.setPhoneNumber(phoneEmailModel.getPhoneNumber());
        customerDAO.updateElement(customerModel);
        logger.info("Phone number for Oauth2 user is set successfully");

    }

    @Override
    public void createUserAfterSocialAuth(UserModel userModel){
            logger.info("Creating new user that was authorized via Google ");
            CustomerModel customer = new CustomerModel();
            customer.setPhoneNumber("Empty");
            userModel.setCustomer(customer);
            userModel.setPassword(passwordEncoder.encode("oauth2user"));
            RoleModel userRole =  findUserRole("USER");
            userModel.setRole(userRole);
            userDAO.addElement(userModel);
            logger.info("User created successfully ");
    }

    @Override
    public boolean isCustomerNumberEmpty(String email) {
        UserModel userModel = userDAO.findUserByEmail(email).orElseThrow(() ->
                new NotFoundInDataBaseException(Constants.USERNOTFOUND));
        CustomerModel customer = userModel.getCustomer();
        return customer.getPhoneNumber().equals("Empty");
    }

    @Override
    public boolean isNumberUnique(String phoneNumber) {
        return customerDAO.findManyByFieldEqual("phoneNumber",phoneNumber).isEmpty();
    }

    @Override
    public String generateTokenForOauthUser(String email){
        UserModel userModel = userDAO.findUserByEmail(email).orElseThrow(() ->
                new NotFoundInDataBaseException(Constants.USERNOTFOUND));
           return jwtUtil.generateToken(userModel.getEmail(),userModel.getRole().getRoleName(), userModel.getId());

    }
}
