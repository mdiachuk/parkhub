package ua.com.parkhub.persistence.impl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ua.com.parkhub.mappers.Mapper;
import ua.com.parkhub.model.CustomerModel;
import ua.com.parkhub.persistence.entities.Customer;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.Optional;

@Repository
public class CustomerDAO extends ElementDAO<Customer, CustomerModel> {

    public CustomerDAO(Mapper<Customer, CustomerModel> entityToModel, Mapper<CustomerModel, Customer> modelToEntity) {
        super(Customer.class, modelToEntity, entityToModel);
    }

    public Optional<CustomerModel> findCustomerByPhoneNumber(String phoneNumber) {
        return findOneByFieldEqual("phoneNumber", phoneNumber);
    }
}
