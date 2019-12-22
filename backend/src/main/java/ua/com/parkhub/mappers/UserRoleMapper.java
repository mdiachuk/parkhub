package ua.com.parkhub.mappers;

import org.springframework.stereotype.Component;
import ua.com.parkhub.model.UserRoleModel;
import ua.com.parkhub.persistence.entities.UserRole;

import java.util.Optional;

@Component
public class UserRoleMapper implements Mapper<UserRole, UserRoleModel> {

    @Override
    public Optional<UserRole> toEntity(UserRoleModel model) {
        UserRole userRole = new UserRole();
        userRole.setRoleName(model.getRoleName());
        userRole.setActive(model.isActive());
        return Optional.of(userRole);
    }

    @Override
    public Optional<UserRoleModel> toModel(UserRole entity) {
        UserRoleModel userRoleModel = new UserRoleModel();
        userRoleModel.setRoleName(entity.getRoleName());
        userRoleModel.setActive(entity.isActive());
        return Optional.of(userRoleModel);
    }
}
