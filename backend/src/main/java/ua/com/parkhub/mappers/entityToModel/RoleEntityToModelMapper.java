package ua.com.parkhub.mappers.entityToModel;

import org.springframework.stereotype.Component;
import ua.com.parkhub.exceptions.ParkHubException;
import ua.com.parkhub.mappers.Mapper;
import ua.com.parkhub.model.enums.RoleModel;
import ua.com.parkhub.persistence.entities.UserRole;

import java.util.Arrays;

@Component
public class RoleEntityToModelMapper implements Mapper<UserRole, RoleModel> {

    @Override
    public RoleModel transform(UserRole from) {
        if (from == null) {
            return null;
        }
        String roleName = from.getRoleName();
        RoleModel roleModel;
        switch (roleName) {
            case "ADMIN":
                roleModel = RoleModel.ADMIN;
                roleModel.setId(from.getId());
                break;
            case "USER":
                roleModel = RoleModel.USER;
                roleModel.setId(from.getId());
                break;
            case "MANAGER":
                roleModel = RoleModel.MANAGER;
                roleModel.setId(from.getId());
                break;
            case "PENDING":
                roleModel = RoleModel.PENDING;
                roleModel.setId(from.getId());
                break;
            default:
                throw new ParkHubException(String.format("Not known role name: %s. Role name may be one of the following: %s).", roleName, Arrays.asList(RoleModel.values())));
        }
        return roleModel;
    }
}

