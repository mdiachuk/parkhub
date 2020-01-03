package ua.com.parkhub.mapper;

import org.springframework.stereotype.Component;
import ua.com.parkhub.dto.RoleDTO;
import ua.com.parkhub.dto.UserDTO;
import ua.com.parkhub.exceptions.ParkHubException;
import ua.com.parkhub.model.RoleModel;
import ua.com.parkhub.model.UserModel;
import ua.com.parkhub.persistence.entities.Entity;
import ua.com.parkhub.persistence.entities.User;
import ua.com.parkhub.persistence.entities.UserRole;

import java.util.Arrays;

@Component
public class UserEntityToModelMapper implements Mapper<User, UserModel> {

    public UserModel transform(User entity) {
        if(entity == null) {
            throw new ParkHubException("User entity to be converted to UserDTO is null.");
        }
        UserModel userModel = new UserModel();
        userModel.setEmail(entity.getEmail());
        userModel.setFirstName(entity.getFirstName());
        userModel.setLastName(entity.getLastName());
        userModel.setId(entity.getId());
        userModel.setNumberOfFaildPassEntering(entity.getNumberOfFaildPassEntering());
        UserRole role = entity.getRole();
        if(role != null && role.getRoleName() != null) {
            String roleName = role.getRoleName().toUpperCase();
            switch (roleName) {
                case "ADMIN":
                    userModel.setRole(RoleModel.ADMIN);
                    break;
                case "USER":
                    userModel.setRole(RoleModel.USER);
                    break;
                case "MANAGER":
                    userModel.setRole(RoleModel.MANAGER);
                    break;
                case "PENDING":
                    userModel.setRole(RoleModel.PENDING);
                    break;
                default:
                    throw new ParkHubException(String.format("Not known role name: %s. Role name may be one of the following: %s).", roleName, Arrays.asList(RoleModel.values())));
            }
        }
        return userModel;
    }
}
