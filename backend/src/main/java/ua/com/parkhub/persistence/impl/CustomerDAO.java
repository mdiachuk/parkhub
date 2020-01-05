package ua.com.parkhub.persistence.impl;

import org.springframework.stereotype.Repository;
import ua.com.parkhub.mappers.Mapper;
import ua.com.parkhub.model.CustomerModel;
import ua.com.parkhub.persistence.entities.Customer;

import java.util.Optional;

@Repository
public class CustomerDAO extends ElementDAO<Customer, CustomerModel> {

    public CustomerDAO(Mapper<Customer, CustomerModel> entityToModel, Mapper<CustomerModel, Customer> modelToEntity) {
        super(Customer.class, modelToEntity, entityToModel);
    }

    public Optional<CustomerModel> findCustomerByPhoneNumber(String phoneNumber) {
        System.out.println("CustomerDAO :" + findOneByFieldEqual("phoneNumber", phoneNumber));
        return findOneByFieldEqual("phoneNumber", phoneNumber);
    }
}

