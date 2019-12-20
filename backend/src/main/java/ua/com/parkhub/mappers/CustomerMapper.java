package ua.com.parkhub.mappers;

import org.springframework.stereotype.Component;
import ua.com.parkhub.model.CustomerModel;
import ua.com.parkhub.persistence.entities.Customer;

import java.util.Optional;

@Component
public class CustomerMapper implements Mapper<Customer, CustomerModel> {

    @Override
    public Optional<Customer> toEntity(CustomerModel model) {
        return Optional.of(new Customer(model.getPhoneNumber(),
                model.isActive()));
    }

    @Override
    public Optional<CustomerModel> toModel(Customer entity) {
        return Optional.of(new CustomerModel(entity.getPhoneNumber(),
                entity.isActive()));
    }
}
