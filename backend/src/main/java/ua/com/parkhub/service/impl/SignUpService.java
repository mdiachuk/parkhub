package ua.com.parkhub.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ua.com.parkhub.exceptions.EmailException;
import ua.com.parkhub.exceptions.NotFoundInDataBaseException;
import ua.com.parkhub.exceptions.PhoneNumberException;
import ua.com.parkhub.model.enums.RoleModel;
import ua.com.parkhub.model.enums.TicketTypeModel;
import ua.com.parkhub.persistence.impl.*;
import ua.com.parkhub.model.*;
import ua.com.parkhub.service.ISignUpService;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class SignUpService implements ISignUpService {

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
    @Override
    public void registerManager(ManagerRegistrationDataModel manager) {
        CustomerModel customer = createCustomer(manager.getUser().getCustomer());
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
    @Override
    public CustomerModel createCustomer(CustomerModel customer) {
        return customerDAO.findCustomerByPhoneNumber(customer.getPhoneNumber()).map(existingCustomer -> {
            logger.info("Customer with phone number={} was found", customer.getPhoneNumber());
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
    @Override
    public String generateDescription(String companyName, String usreouCode, String comment) {
        return "Company: <" + companyName + "> " +
                "USREOU: <" + usreouCode + "> " +
                "Comment: <" + comment + ">";
    }
    @Override
    public RoleModel findUserRole(String name) {
        return userRoleDAO.findUserRoleByRoleName(name).orElseThrow(() ->
                new NotFoundInDataBaseException("Role was not found by name=" + name));
    }
    @Override
    public TicketTypeModel findSupportTicketType(String type) {
        return supportTicketTypeDAO.findSupportTicketTypeByType(type).orElseThrow(() ->
                new NotFoundInDataBaseException("Support ticket type was not found by type=" + type));
    }
    @Override
    public List<UserModel> findSolvers(String role) {
        List<UserModel> solvers = userDAO.findUsersByRoleId(findUserRole(role).getId());
        if (solvers.isEmpty()) {
            throw new NotFoundInDataBaseException("Solvers were not found by role=" + role);
        }
        return solvers;
    }

    @Override
    public boolean isUserPresentByEmail(String email) {
        return userDAO.findOneByFieldEqual("email", email).isPresent();
    }

    @Override
    public void setPhoneNumberForAuthUser(PhoneEmailModel phoneEmailModel) {
        if(userDAO.findOneByFieldEqual("email", phoneEmailModel.getEmail()).isPresent()){
            CustomerModel customerModel = userDAO.setOauthUserPhone(phoneEmailModel);
            customerDAO.updateElement(customerModel);
        }
    }

    @Override
    public void createUserAfterSocialAuth(AuthUserModel userModel){
        if(!userDAO.findUserByEmail(userModel.getEmail()).isPresent()){
            UserModel user = new UserModel();
            CustomerModel customer = new CustomerModel();
            customer.setPhoneNumber("Empty");
            user.setEmail(userModel.getEmail());
            user.setCustomer(customer);
            user.setPassword(passwordEncoder.encode("oauth2user"));
            user.setLastName(userModel.getLastName());
            user.setFirstName(userModel.getFirstName());
            RoleModel userRole = userRoleDAO.findOneByFieldEqual("roleName", "USER").get();
            user.setRole(userRole);
            userDAO.addElement(user);
        }
    }


    @Override
    public boolean isCustomerNumberEmpty(String email) {
        UserModel user = userDAO.findOneByFieldEqual("email", email).get();
        CustomerModel customer = user.getCustomer();
        return customer.getPhoneNumber().equals("Empty");
    }

    @Override
    public boolean signUpUser(UserModel userModel){

        try {
            userDAO.addElement( createUser( userModel, createCustomer(userModel.getCustomer())));
            return true;
        } catch (Exception e){
            logger.error(""+e);
        }
        return false;
    }
    @Override
    public boolean isNumberUnique(String phoneNumber) {
        return customerDAO.findManyByFieldEqual("phoneNumber",phoneNumber).isEmpty();
    }



}
