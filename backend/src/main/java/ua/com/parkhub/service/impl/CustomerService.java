package ua.com.parkhub.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.com.parkhub.exceptions.ParkHubException;
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

    private CustomerModel addCustomerAndGet(String phoneNumber) {
        CustomerModel customerModel = new CustomerModel();
        customerModel.setPhoneNumber(phoneNumber);
        customerDAO.addElement(customerModel);
        Optional<CustomerModel> optionalUser = customerDAO.findCustomerByPhoneNumber(phoneNumber);
        if (optionalUser.isPresent()) {
            return optionalUser.get();
        }
        throw new ParkHubException("No Customer found with phone number " + phoneNumber);
    }

    @Transactional
    public CustomerModel findCustomerByPhoneNumberOrAdd(String phoneNumber) {
        return customerDAO.findCustomerByPhoneNumber(phoneNumber).orElseGet(() -> addCustomerAndGet(phoneNumber));
    }
}
