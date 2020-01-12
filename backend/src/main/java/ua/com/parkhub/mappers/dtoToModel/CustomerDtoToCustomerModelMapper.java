package ua.com.parkhub.mappers.dtoToModel;

import org.springframework.stereotype.Component;
import ua.com.parkhub.dto.CustomerDTO;
import ua.com.parkhub.mappers.Mapper;
import ua.com.parkhub.model.CustomerModel;

@Component
public class CustomerDtoToCustomerModelMapper implements Mapper<CustomerDTO, CustomerModel> {

    @Override
    public CustomerModel transform(CustomerDTO from) {
        if (from == null) {
            return null;
        }
        CustomerModel customerModel = new CustomerModel();
        customerModel.setId(from.getId());
        customerModel.setActive(from.isActive());
        customerModel.setPhoneNumber(from.getPhoneNumber());
        return customerModel;
    }
}
