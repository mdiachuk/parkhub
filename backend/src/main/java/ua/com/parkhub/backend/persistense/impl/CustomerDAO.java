package ua.com.parkhub.backend.persistense.impl;

import ua.com.parkhub.backend.persistense.entities.Customer;

public class CustomerDAO extends ElementDAO<Customer> {
    public CustomerDAO() {
        super(Customer.class);
    }
}
