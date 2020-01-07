package ua.com.parkhub.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.parkhub.persistence.entities.Customer;
import ua.com.parkhub.persistence.impl.CustomerDAO;

//import ua.com.parkhub.model.Customer;

@Service
public class CustomerService
//        implements ICustomerService
{

    private final CustomerDAO customerDAO;
    private final ModelMapper mapper;

    @Autowired
    public CustomerService(CustomerDAO customerDAO, ModelMapper mapper) {
        this.customerDAO = customerDAO;
        this.mapper = mapper;
    }

    private Customer addCustomerAndGet(String phoneNumber) {
//        Customer customerEntity = new Customer();
//        customerEntity.setPhoneNumber(phoneNumber);
//        customerDAO.addElement(customerEntity);
//        Optional<Customer> optionalUser = customerDAO.findCustomerByPhoneNumber(phoneNumber);
//        if (optionalUser.isPresent()) {
//            return optionalUser.get();
//        }
//        throw new ParkHubException("No Customer found with phone number " + phoneNumber);
        return null;
    }

//    @Transactional
//    public ua.com.parkhub.model.Customer findCustomerByPhoneNumberOrAdd(String phoneNumber) {
////        Customer customerEntity = customerDAO.findCustomerByPhoneNumber(phoneNumber).orElseGet(() -> addCustomerAndGet(phoneNumber));
////        return mapper.map(customerEntity, ua.com.parkhub.model.Customer.class);
//        return null;
//    }
}
