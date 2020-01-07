package ua.com.parkhub.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.com.parkhub.exceptions.CustomerException;
import ua.com.parkhub.exceptions.ParkHubException;
//import ua.com.parkhub.model.Customer;
import ua.com.parkhub.exceptions.StatusCode;
import ua.com.parkhub.model.Customer;
import ua.com.parkhub.model.CustomerModel;
import ua.com.parkhub.persistence.impl.CustomerDAO;
import ua.com.parkhub.service.ICustomerService;

import java.util.Optional;

@Service
@Transactional
public class CustomerService implements ICustomerService {

    private final CustomerDAO customerDAO;

  /*  @Autowired
    public CustomerService(CustomerDAO customerDAO, ModelMapper mapper) {
        this.customerDAO = customerDAO;
        this.mapper = mapper;
    }

    private Customer addCustomerAndGet(String phoneNumber) {
        Customer customerEntity = new Customer();
        customerEntity.setPhoneNumber(phoneNumber);
        customerDAO.addElement(customerEntity);
        Optional<Customer> optionalUser = customerDAO.findCustomerByPhoneNumber(phoneNumber);
        if (optionalUser.isPresent()) {
            return optionalUser.get();
        }
        throw new ParkHubException("No Customer found with phone number " + phoneNumber);
    }

    @Transactional
    public ua.com.parkhub.model.Customer findCustomerByPhoneNumberOrAdd(String phoneNumber) {
        Customer customerEntity = customerDAO.findCustomerByPhoneNumber(phoneNumber).orElseGet(() -> addCustomerAndGet(phoneNumber));
        return mapper.map(customerEntity, ua.com.parkhub.model.Customer.class);
    }*/

    @Autowired
    public CustomerService(CustomerDAO customerDAO) {
        this.customerDAO = customerDAO;
    }

    @Override
    public Customer findCustomerByPhoneNumberOrAdd(String phoneNumber) {
        return null;
    }

    public CustomerModel findByPhoneNumber(String phoneNumber){
        return customerDAO.findCustomerByPhoneNumber(phoneNumber).orElseThrow(()-> new CustomerException(StatusCode.CUSTOMER_NOT_FOUND));
    }
}
