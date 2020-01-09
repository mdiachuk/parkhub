package ua.com.parkhub.mappers.modelToEntity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.com.parkhub.mappers.Mapper;
import ua.com.parkhub.model.UserModel;
import ua.com.parkhub.persistence.entities.User;

import java.util.stream.Collectors;

@Component
public class UserModelToEntityMapper implements Mapper<UserModel, User> {

    private CustomerModelToEntityMapper customerModelToEntityMapper;
    private SupportTicketModelToEntityMapper supportTicketModelToEntityMapper;
    private RoleModelToEntityMapper roleModelToEntityMapper;

    @Autowired
    public UserModelToEntityMapper(CustomerModelToEntityMapper customerModelToEntityMapper,
                                   SupportTicketModelToEntityMapper supportTicketModelToEntityMapper,
                                   RoleModelToEntityMapper roleModelToEntityMapper) {
        this.customerModelToEntityMapper = customerModelToEntityMapper;
        this.supportTicketModelToEntityMapper = supportTicketModelToEntityMapper;
        this.roleModelToEntityMapper = roleModelToEntityMapper;
    }

    @Override
    public User transform(UserModel from) {
        if(from == null) {
            return null;
        }
        User user = new User();
        user.setId(from.getId());
        user.setCustomer(customerModelToEntityMapper.transform(from.getCustomer()));
        user.setEmail(from.getEmail());
        user.setFirstName(from.getFirstName());
        user.setLastName(from.getLastName());
        user.setPassword(from.getPassword());
        user.setRole(roleModelToEntityMapper.transform(from.getRole()));
//        user.setTickets(from.getTickets().stream().map(supportTicketModelToEntityMapper::transform).collect(Collectors.toList()));
        return user;
    }
}
