package ua.com.parkhub.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.com.parkhub.exceptions.ParkHubException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.com.parkhub.exceptions.CustomerException;
import ua.com.parkhub.exceptions.StatusCode;
import ua.com.parkhub.model.CustomerModel;
import ua.com.parkhub.persistence.impl.CustomerDAO;
import ua.com.parkhub.service.ICustomerService;

import java.util.Optional;

@Service
public class CustomerService implements ICustomerService {

    private final CustomerDAO customerDAO;

    @Autowired
    public CustomerService(CustomerDAO customerDAO) {
        this.customerDAO = customerDAO;
    }

    private CustomerModel addCustomer(String phoneNumber) {
        CustomerModel customerModel = new CustomerModel();
        customerModel.setPhoneNumber(phoneNumber);
        customerDAO.addElement(customerModel);
        return customerDAO.findCustomerByPhoneNumber(phoneNumber).orElseThrow(() -> new ParkHubException("No Customer found with phone number " + phoneNumber));
    }


    @Override
    public CustomerModel findCustomerByPhoneNumber(String phoneNumber) {
        return customerDAO.findCustomerByPhoneNumber(phoneNumber).orElseThrow(() -> new CustomerException(StatusCode.CUSTOMER_NOT_FOUND));
    }

    @Transactional
    public CustomerModel findCustomerByPhoneNumberOrAdd(String phoneNumber) {
        return customerDAO.findCustomerByPhoneNumber(phoneNumber).orElseGet(() -> addCustomer(phoneNumber));
    }
}
