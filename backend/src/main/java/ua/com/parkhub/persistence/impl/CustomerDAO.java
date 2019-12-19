package ua.com.parkhub.persistence.impl;

import org.springframework.stereotype.Repository;
import ua.com.parkhub.persistence.entities.Customer;

@Repository
public class CustomerDAO extends ElementDAO<Customer> {

    public CustomerDAO() {
        super(Customer.class);
    }
}

