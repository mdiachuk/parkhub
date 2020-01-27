package ua.com.parkhub.service;

import ua.com.parkhub.model.*;

public interface ISignUpService {

    void registerUser(UserModel user);
    void registerManager(ManagerRegistrationDataModel manager);
    CustomerModel createCustomer(CustomerModel customer);
    UserModel createUser(UserModel user);
    SupportTicketModel createTicket(String description, CustomerModel customer);
    boolean isUserPresentByEmail(String email);
    void setPhoneNumberForAuthUser(PhoneEmailModel phoneEmailModel);
    void createUserAfterSocialAuth(UserModel userModel);
    boolean isCustomerNumberEmpty(String email);
    boolean isNumberUnique(String phoneNumber);
    String generateTokenForOauthUser(String email);
}
