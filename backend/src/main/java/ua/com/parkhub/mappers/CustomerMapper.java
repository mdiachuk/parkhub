package ua.com.parkhub.mappers;

import org.springframework.stereotype.Component;
import ua.com.parkhub.model.CustomerModel;
import ua.com.parkhub.persistence.entities.Customer;

import java.util.Optional;

@Component
public class CustomerMapper implements Mapper<Customer, CustomerModel> {

    @Override
    public Optional<Customer> toEntity(CustomerModel model) {
        Customer customer = new Customer();
        customer.setPhoneNumber(model.getPhoneNumber());
        customer.setActive(model.isActive());
        return Optional.of(customer);
    }

    @Override
    public Optional<CustomerModel> toModel(Customer entity) {
        CustomerModel customerModel = new CustomerModel();
        customerModel.setPhoneNumber(entity.getPhoneNumber());
        customerModel.setActive(entity.isActive());
        return Optional.of(customerModel);
    }
}
