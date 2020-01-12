
package ua.com.parkhub.service;

import ua.com.parkhub.model.CustomerModel;

public interface ICustomerService {
    CustomerModel findCustomerByPhoneNumber(String phoneNumber);
    CustomerModel findCustomerByPhoneNumberOrAdd(String pHoneNumber);
}
