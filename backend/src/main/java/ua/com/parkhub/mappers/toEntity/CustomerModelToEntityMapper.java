package ua.com.parkhub.mappers.toEntity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import ua.com.parkhub.mappers.Mapper;
import ua.com.parkhub.model.CustomerModel;
import ua.com.parkhub.persistence.entities.Customer;

@Component
public class CustomerModelToEntityMapper implements Mapper<CustomerModel, Customer> {

//    UserModelToEntityMapper userModelToEntityMapper;
//
//    @Autowired
//    public CustomerModelToEntityMapper(@Lazy UserModelToEntityMapper userModelToEntityMapper) {
////        this.supportTicketEntityToModelMapper = supportTicketEntityToModelMapper;
//        this.userModelToEntityMapper = userModelToEntityMapper;
//    }

    @Override
    public Customer transform(CustomerModel from) {
        Customer entity = new Customer();
        entity.setId(from.getId());
        entity.setActive(from.isActive());
        entity.setPhoneNumber(from.getPhoneNumber());
//        entity.setUser(userModelToEntityMapper.transform(from.getUser()));
//        entity.setSupportTickets(from.getSupportTickets().stream().map(supportTicketEntityToModelMapper::transform).collect(Collectors.toList()));
        return entity;
    }
}
