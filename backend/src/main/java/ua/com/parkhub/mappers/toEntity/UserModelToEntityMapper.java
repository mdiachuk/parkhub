package ua.com.parkhub.mappers.toEntity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.com.parkhub.exceptions.ParkHubException;
import ua.com.parkhub.mappers.Mapper;
import ua.com.parkhub.model.UserModel;
import ua.com.parkhub.persistence.entities.User;
import ua.com.parkhub.persistence.entities.UserRole;

import java.util.stream.Collectors;

@Component
public class UserModelToEntityMapper implements Mapper<UserModel, User> {

    private CustomerModelToEntityMapper customerModelToEntityMapper;
    private SupportTicketModelToEntityMapper supportTicketModelToEntityMapper;
    private UserRoleModelToEntityMapper userRoleModelToEntityMapper;

    @Autowired
    public UserModelToEntityMapper(CustomerModelToEntityMapper customerModelToEntityMapper,
                                   SupportTicketModelToEntityMapper supportTicketModelToEntityMapper,
                                   UserRoleModelToEntityMapper userRoleModelToEntityMapper) {
        this.customerModelToEntityMapper = customerModelToEntityMapper;
        this.supportTicketModelToEntityMapper = supportTicketModelToEntityMapper;
        this.userRoleModelToEntityMapper = userRoleModelToEntityMapper;
    }

    @Override
    public User transform(UserModel model) {
        if(model == null) {
            throw new ParkHubException("UserModel to be mapped to User entity is null.");
        }
        User userEntity = new User();
        userEntity.setId(model.getId());
        userEntity.setCustomer(customerModelToEntityMapper.transform(model.getCustomer()));
        userEntity.setEmail(model.getEmail());
        userEntity.setFirstName(model.getFirstName());
        userEntity.setLastName(model.getLastName());
        userEntity.setPassword(model.getPassword());
        userEntity.setRole(userRoleModelToEntityMapper.transform(model.getRole()));
        return userEntity;
    }
}
