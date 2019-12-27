package ua.com.parkhub.persistence.impl;

import org.springframework.stereotype.Repository;
import ua.com.parkhub.mappers.Mapper;
import ua.com.parkhub.model.CustomerModel;
import ua.com.parkhub.model.ParkingModel;
import ua.com.parkhub.persistence.entities.Customer;
import ua.com.parkhub.persistence.entities.Parking;

@Repository
public class CustomerDAO extends ElementDAO<Customer, CustomerModel> {

    public CustomerDAO(Mapper<Customer, CustomerModel> entityToModel, Mapper<CustomerModel, Customer> modelToEntity) {
        super(Customer.class, modelToEntity, entityToModel);
    }
}

