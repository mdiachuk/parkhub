package ua.com.parkhub.mapper;

import org.springframework.stereotype.Component;
import ua.com.parkhub.exceptions.ParkHubException;
import ua.com.parkhub.model.UserModel;
import ua.com.parkhub.persistence.entities.User;
import ua.com.parkhub.persistence.entities.UserRole;

@Component
public class UserModelToEntityMapper implements Mapper<UserModel, User> {

    public User transform(UserModel model) {
        if(model == null) {
            throw new ParkHubException("UserModel to be mapped to User entity is null.");
        }
        User userEntity = new User();
        userEntity.setEmail(model.getEmail());
        userEntity.setFirstName(model.getFirstName());
        userEntity.setLastName(model.getLastName());
        userEntity.setNumberOfFaildPassEntering(model.getNumberOfFaildPassEntering());
        UserRole role = new UserRole();
        if (model.getRole() != null) {
            role.setRoleName(model.getRole().getRoleName());
        }
        userEntity.setRole(role);
        return userEntity;
    }
}
