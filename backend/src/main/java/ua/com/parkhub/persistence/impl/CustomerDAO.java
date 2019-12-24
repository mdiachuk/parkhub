package ua.com.parkhub.persistence.impl;

import org.springframework.stereotype.Repository;
import ua.com.parkhub.persistence.entities.Customer;

import java.util.Optional;

@Repository
public class CustomerDAO extends ElementDAO<Customer> {

    public CustomerDAO() {
        super(Customer.class);
    }

    public Optional<Customer> findCustomerByPhoneNumber(String phoneNumber) {
        return findOneByFieldEqual("phoneNumber", phoneNumber);
    }

}

