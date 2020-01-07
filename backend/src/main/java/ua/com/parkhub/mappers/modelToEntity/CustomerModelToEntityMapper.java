package ua.com.parkhub.mappers.modelToEntity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.com.parkhub.mappers.Mapper;
import ua.com.parkhub.model.CustomerModel;
import ua.com.parkhub.persistence.entities.Customer;

import java.util.stream.Collectors;

@Component
public class CustomerModelToEntityMapper implements Mapper<CustomerModel, Customer> {

    SupportTicketModelToEntityMapper supportTicketModelToEntityMapper;

    @Autowired
    public CustomerModelToEntityMapper(SupportTicketModelToEntityMapper supportTicketModelToEntityMapper) {
        this.supportTicketModelToEntityMapper = supportTicketModelToEntityMapper;
    }

    @Override
    public Customer transform(CustomerModel from) {
        Customer customer = new Customer();
        customer.setId(from.getId());
        customer.setActive(from.isActive());
        customer.setPhoneNumber(from.getPhoneNumber());
        customer.setSupportTickets(from.getSupportTickets().stream()
                .map(supportTicketModelToEntityMapper::transform)
        .collect(Collectors.toList()));
        return customer;
    }
}
