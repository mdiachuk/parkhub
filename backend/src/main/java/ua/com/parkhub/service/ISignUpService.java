package ua.com.parkhub.service;

import ua.com.parkhub.model.*;
import ua.com.parkhub.model.enums.RoleModel;
import ua.com.parkhub.model.enums.TicketTypeModel;

import java.util.List;

public interface ISignUpService {

    void registerManager(ManagerRegistrationDataModel manager);
    CustomerModel createCustomer(CustomerModel customer);
    UserModel createUser(UserModel user, CustomerModel customer);
    SupportTicketModel createTicket(String description, CustomerModel customer);
    String generateDescription(long id, String companyName, String usreouCode, String comment);
    RoleModel findUserRole(String name);
    TicketTypeModel findSupportTicketType(String type);
    List<UserModel> findSolvers(String role);
    boolean isUserPresentByEmail(String email);
    void setPhoneNumberForAuthUser(PhoneEmailModel phoneEmailModel);
    void createUserAfterSocialAuth(AuthUserModel userModel);
    boolean isCustomerNumberEmpty(String email);
    boolean signUpUser(UserModel userModel);
    boolean isNumberUnique(String phoneNumber);
    String generateTokenForOauthUser(String email);
}
