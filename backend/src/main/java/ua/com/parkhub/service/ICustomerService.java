package ua.com.parkhub.service;

import ua.com.parkhub.model.CustomerModel;

public interface ICustomerService {
    CustomerModel findCustomerByPhoneNumberOrAdd(String phoneNumber);
}
