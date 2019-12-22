package ua.com.parkhub.persistence.impl;

import org.springframework.stereotype.Repository;
import ua.com.parkhub.persistence.entities.CustomerEntity;

import java.util.Optional;

@Repository
public class CustomerDAO extends ElementDAO<CustomerEntity> {

    public CustomerDAO() {
        super(CustomerEntity.class);
    }

    public Optional<CustomerEntity> findCustomerByPhoneNumber(String phoneNumber) {
        return findOneByFieldEqual("phoneNumber", phoneNumber);
    }
}

