package ua.com.parkhub.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.com.parkhub.exceptions.ParkHubException;
import ua.com.parkhub.model.Customer;
import ua.com.parkhub.persistence.entities.CustomerEntity;
import ua.com.parkhub.persistence.impl.CustomerDAO;
import ua.com.parkhub.service.ICustomerService;

import java.util.Optional;

@Service
public class CustomerService implements ICustomerService {

    private final CustomerDAO customerDAO;
    private final ModelMapper mapper;

    @Autowired
    public CustomerService(CustomerDAO customerDAO, ModelMapper mapper) {
        this.customerDAO = customerDAO;
        this.mapper = mapper;
    }

    private CustomerEntity addCustomerAndGet(String phoneNumber) {
        CustomerEntity customerEntity = new CustomerEntity();
        customerEntity.setPhoneNumber(phoneNumber);
        customerDAO.addElement(customerEntity);
        Optional<CustomerEntity> optionalUser = customerDAO.findCustomerByPhoneNumber(phoneNumber);
        if (optionalUser.isPresent()) {
            return optionalUser.get();
        }
        throw new ParkHubException("No Customer found with phone number " + phoneNumber);
    }

    @Transactional
    public Customer findCustomerByPhoneNumberOrAdd(String phoneNumber) {
        CustomerEntity customerEntity = customerDAO.findCustomerByPhoneNumber(phoneNumber).orElseGet(() -> addCustomerAndGet(phoneNumber));
        return mapper.map(customerEntity, Customer.class);
    }
}
