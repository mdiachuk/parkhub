package ua.com.parkhub.mappers.toModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.com.parkhub.mapper.Mapper;
import ua.com.parkhub.model.CustomerModel;
import ua.com.parkhub.persistence.entities.Customer;

import java.util.stream.Collectors;

@Component
public class CustomerEntityToModelMapper implements Mapper<Customer, CustomerModel> {

    SupportTicketEntityToModelMapper supportTicketEntityToModelMapper;

    @Autowired
    public CustomerEntityToModelMapper(SupportTicketEntityToModelMapper supportTicketEntityToModelMapper) {
        this.supportTicketEntityToModelMapper = supportTicketEntityToModelMapper;
    }

    @Override
    public CustomerModel transform(Customer from) {
        CustomerModel customerModel = new CustomerModel();
        customerModel.setId(from.getId());
        customerModel.setActive(from.isActive());
        customerModel.setPhoneNumber(from.getPhoneNumber());
        customerModel.setSupportTickets(from.getSupportTickets().stream().map(supportTicketEntityToModelMapper::transform).collect(Collectors.toList()));
        return customerModel;
    }
}
