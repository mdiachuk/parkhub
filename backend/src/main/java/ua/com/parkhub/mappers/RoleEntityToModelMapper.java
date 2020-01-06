package ua.com.parkhub.mappers;

import org.springframework.stereotype.Component;
import ua.com.parkhub.exceptions.ParkHubException;
import ua.com.parkhub.model.RoleModel;
import ua.com.parkhub.persistence.entities.UserRole;

import java.util.Arrays;

@Component
public class RoleEntityToModelMapper implements Mapper<UserRole, RoleModel> {

    @Override
    public RoleModel transform(UserRole entity) {
        String roleName = entity.getRoleName();
        RoleModel roleModel;
        switch (roleName) {
            case "ADMIN":
                roleModel = RoleModel.ADMIN;
                roleModel.setId(entity.getId());
                break;
            case "USER":
                roleModel = RoleModel.USER;
                roleModel.setId(entity.getId());
                break;
            case "MANAGER":
                roleModel = RoleModel.MANAGER;
                roleModel.setId(entity.getId());
                break;
            case "PENDING":
                roleModel = RoleModel.PENDING;
                roleModel.setId(entity.getId());
                break;
            default:
                throw new ParkHubException(String.format("Not known role name: %s. Role name may be one of the following: %s).", roleName, Arrays.asList(RoleModel.values())));
        }
            return roleModel;
    }
}
