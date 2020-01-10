package ua.com.parkhub.mappers.modelToEntity;

import org.springframework.stereotype.Component;
import ua.com.parkhub.mappers.Mapper;
import ua.com.parkhub.model.RoleModel;
import ua.com.parkhub.persistence.entities.UserRole;

@Component
public class RoleModelToEntityMapper implements Mapper<RoleModel, UserRole> {

    @Override
    public UserRole transform(RoleModel from) {
        if (from == null) {
            return null;
        }
        UserRole userRole = new UserRole();
        userRole.setId(from.getId());
        userRole.setRoleName(from.getRoleName());
        return userRole;
    }
}

