package ua.com.parkhub.mappers.modelToEntity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.com.parkhub.exceptions.ParkHubException;
import ua.com.parkhub.mappers.Mapper;
import ua.com.parkhub.model.UserModel;
import ua.com.parkhub.persistence.entities.User;

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
    public User transform(UserModel model) {
        if(model == null) {
            throw new ParkHubException("UserModel to be mapped to User entity is null.");
        }
        User user = new User();
        user.setId(model.getId());
        user.setCustomer(customerModelToEntityMapper.transform(model.getCustomer()));
        user.setEmail(model.getEmail());
        user.setFirstName(model.getFirstName());
        user.setLastName(model.getLastName());
        user.setPassword(model.getPassword());
        user.setRole(roleModelToEntityMapper.transform(model.getRole()));
        return user;
    }
}
