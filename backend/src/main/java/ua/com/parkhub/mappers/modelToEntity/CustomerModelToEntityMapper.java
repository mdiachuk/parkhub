package ua.com.parkhub.mappers.modelToEntity;

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
        Customer customer = new Customer();
        customer.setId(from.getId());
        customer.setActive(from.isActive());
        customer.setPhoneNumber(from.getPhoneNumber());
//        customer.setUser(userModelToEntityMapper.transform(from.getUser()));
//        customer.setSupportTickets(from.getSupportTickets().stream().map(supportTicketEntityToModelMapper::transform).collect(Collectors.toList()));
        return customer;
    }
}
