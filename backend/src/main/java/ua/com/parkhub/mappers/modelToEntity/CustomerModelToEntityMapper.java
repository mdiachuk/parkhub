package ua.com.parkhub.mappers.modelToEntity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.com.parkhub.mappers.Mapper;
import ua.com.parkhub.model.CustomerModel;
import ua.com.parkhub.persistence.entities.Customer;

import java.util.stream.Collectors;

@Component
public class CustomerModelToEntityMapper implements Mapper<CustomerModel, Customer> {

    @Override
    public Customer transform(CustomerModel from) {
        if (from == null) {
            return null;
        }
        Customer customer = new Customer();
        customer.setId(from.getId());
        customer.setActive(from.isActive());
        customer.setPhoneNumber(from.getPhoneNumber());
        return customer;
    }
}
