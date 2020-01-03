package ua.com.parkhub.mappers.toEntity;

import org.springframework.stereotype.Component;
import ua.com.parkhub.model.CustomerModel;
import ua.com.parkhub.model.Mapper;
import ua.com.parkhub.persistence.entities.Customer;

@Component
public class CustomerModelToEntityMapper implements Mapper<CustomerModel, Customer> {
    @Override
    public Customer transform(CustomerModel from) {
        return null;
    }
}
