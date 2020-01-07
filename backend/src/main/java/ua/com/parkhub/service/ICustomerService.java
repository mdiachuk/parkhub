package ua.com.parkhub.service;

import ua.com.parkhub.model.Customer;
import ua.com.parkhub.model.CustomerModel;

public interface ICustomerService {
    Customer findCustomerByPhoneNumberOrAdd(String phoneNumber);
    CustomerModel findByPhoneNumber(String phoneNumber);
}
