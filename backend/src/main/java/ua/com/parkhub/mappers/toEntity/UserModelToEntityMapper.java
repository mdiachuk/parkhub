package ua.com.parkhub.mappers.toEntity;

import org.springframework.stereotype.Component;
import ua.com.parkhub.exceptions.ParkHubException;
import ua.com.parkhub.mapper.Mapper;
import ua.com.parkhub.model.UserModel;
import ua.com.parkhub.persistence.entities.User;

@Component
public class UserModelToEntityMapper implements Mapper<UserModel, User> {

    private final CustomerModelToEntityMapper customerModelToEntityMapper;
    private final RoleModelToEntityMapper roleModelToEntityMapper;

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
        userEntity.setPassword(model.getPassword());
        userEntity.setNumberOfFaildPassEntering(model.getNumberOfFaildPassEntering());
        userEntity.setRole(roleModelToEntityMapper.transform(model.getRole()));
        userEntity.setCustomer(customerModelToEntityMapper.transform(model.getCustomer()));

        return userEntity;
    }
}
