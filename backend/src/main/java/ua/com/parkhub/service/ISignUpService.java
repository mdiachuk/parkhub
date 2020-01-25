package ua.com.parkhub.service;

import ua.com.parkhub.model.*;

public interface ISignUpService {

    void registerManager(ManagerRegistrationDataModel manager);
    CustomerModel createCustomer(CustomerModel customer);
    UserModel createUser(UserModel user);
    SupportTicketModel createTicket(String description, CustomerModel customer);
    boolean isUserPresentByEmail(String email);
    void setPhoneNumberForAuthUser(PhoneEmailModel phoneEmailModel);
    void createUserAfterSocialAuth(AuthUserModel userModel);
    boolean isCustomerNumberEmpty(String email);
    boolean signUpUser(UserModel userModel);
    boolean isNumberUnique(String phoneNumber);
    UserModel findUserbyEmail(String email);
}
