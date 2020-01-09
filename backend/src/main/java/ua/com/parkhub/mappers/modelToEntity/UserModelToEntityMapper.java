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
    private RoleModelToEntityMapper roleModelToEntityMapper;

    @Autowired
    public UserModelToEntityMapper(CustomerModelToEntityMapper customerModelToEntityMapper, RoleModelToEntityMapper roleModelToEntityMapper) {
        this.customerModelToEntityMapper = customerModelToEntityMapper;
        this.roleModelToEntityMapper = roleModelToEntityMapper;
    }

    @Override
    public User transform(UserModel from) {
        if(from == null) {
            return null;
        }
        User user = new User();
        user.setEmail(from.getEmail());
        user.setFirstName(from.getFirstName());
        user.setLastName(from.getLastName());
        user.setId(from.getId());
        user.setPassword(from.getPassword());
        user.setCustomer(customerModelToEntityMapper.transform(from.getCustomer()));
        user.setNumberOfFailedPassEntering(from.getNumberOfFailedPassEntering());
        user.setRole(roleModelToEntityMapper.transform(from.getRole()));
        return user;
    }
}
