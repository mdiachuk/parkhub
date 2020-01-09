package ua.com.parkhub.mappers.modelToEntity;

import org.springframework.stereotype.Component;
import ua.com.parkhub.mappers.Mapper;
import ua.com.parkhub.model.RoleModel;
import ua.com.parkhub.persistence.entities.UserRole;

@Component
public class RoleModelToEntityMapper implements Mapper<RoleModel, UserRole> {
    @Override
    public ua.com.parkhub.persistence.entities.UserRole transform(RoleModel model) {
        UserRole role = new UserRole();
        role.setId(model.getId());
        role.setRoleName(model.getRoleName());
        return role;
    }
}
