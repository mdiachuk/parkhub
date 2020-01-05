package ua.com.parkhub.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.com.parkhub.exceptions.ParkHubException;
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
    public User transform(UserModel model) {
        if(model == null) {
            throw new ParkHubException("UserModel to be mapped to User entity is null.");
        }
        User userEntity = new User();
        userEntity.setEmail(model.getEmail());
        userEntity.setFirstName(model.getFirstName());
        userEntity.setLastName(model.getLastName());
        userEntity.setId(model.getId());
        userEntity.setPassword(model.getPassword());
        userEntity.setCustomer(customerModelToEntityMapper.transform(model.getCustomer()));
        userEntity.setNumberOfFaildPassEntering(model.getNumberOfFaildPassEntering());
        userEntity.setRole(roleModelToEntityMapper.transform(model.getRole()));
        return userEntity;
    }
}
