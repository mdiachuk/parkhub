package ua.com.parkhub.mappers.toModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import ua.com.parkhub.mappers.Mapper;
import ua.com.parkhub.model.CustomerModel;
import ua.com.parkhub.persistence.entities.Customer;

import java.util.stream.Collectors;

@Component
public class CustomerEntityToModelMapper implements Mapper<Customer, CustomerModel> {

//    SupportTicketEntityToModelMapper supportTicketEntityToModelMapper;
//    UserEntityToModelMapper userEntityToModelMapper;

//    @Autowired
//    public CustomerEntityToModelMapper(SupportTicketEntityToModelMapper supportTicketEntityToModelMapper,
//                                       @Lazy UserEntityToModelMapper userEntityToModelMapper) {
//        this.supportTicketEntityToModelMapper = supportTicketEntityToModelMapper;
//        this.userEntityToModelMapper = userEntityToModelMapper;
//    }

    @Override
    public CustomerModel transform(Customer from) {
        if (from == null) {
            return null;
        }
        CustomerModel customerModel = new CustomerModel();
        customerModel.setId(from.getId());
        customerModel.setActive(from.isActive());
        customerModel.setPhoneNumber(from.getPhoneNumber());
//        customerModel.setUser(userEntityToModelMapper.transform(from.getUser()));
//        customerModel.setSupportTickets(from.getSupportTickets().stream().map(supportTicketEntityToModelMapper::transform).collect(Collectors.toSet()));
        return customerModel;
    }
}
