package ua.com.parkhub.mappers.fromEntityToModel;

import org.springframework.stereotype.Component;
import ua.com.parkhub.mappers.Mapper;
import ua.com.parkhub.model.CustomerModel;
import ua.com.parkhub.persistence.entities.Customer;

@Component
public class CustomerEntityToModelMapper implements Mapper<Customer, CustomerModel> {

    @Override
    public CustomerModel transform(Customer from) {
        CustomerModel customerModel = new CustomerModel();
        customerModel.setId(from.getId());
        customerModel.setActive(from.isActive());
        customerModel.setPhoneNumber(from.getPhoneNumber());
        return customerModel;
    }
}
