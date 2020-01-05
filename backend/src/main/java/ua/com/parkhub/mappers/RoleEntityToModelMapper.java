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
        switch (roleName) {
            case "ADMIN":
                return RoleModel.ADMIN;
            case "USER":
                return RoleModel.USER;
            case "MANAGER":
                return RoleModel.MANAGER;
            case "PENDING":
                return RoleModel.PENDING;
            default:
                throw new ParkHubException(String.format("Not known role name: %s. Role name may be one of the following: %s).", roleName, Arrays.asList(RoleModel.values())));
        }

    }
}
