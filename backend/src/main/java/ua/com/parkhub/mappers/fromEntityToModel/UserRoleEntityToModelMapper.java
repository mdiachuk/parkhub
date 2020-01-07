package ua.com.parkhub.mappers.fromEntityToModel;

import org.springframework.stereotype.Component;
import ua.com.parkhub.mappers.Mapper;
import ua.com.parkhub.model.RoleModel;
import ua.com.parkhub.persistence.entities.UserRole;

@Component
public class UserRoleEntityToModelMapper implements Mapper<UserRole, RoleModel> {
    @Override
    public RoleModel transform(UserRole from) {
        RoleModel userRoleModel = new RoleModel();
        userRoleModel.setId(from.getId());
        userRoleModel.setActive(from.isActive());
        userRoleModel.setRoleName(from.getRoleName());
        return userRoleModel;
    }
}
