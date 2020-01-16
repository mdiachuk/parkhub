package ua.com.parkhub.mappers.modelToEntity;

import org.springframework.stereotype.Component;
import ua.com.parkhub.mappers.Mapper;
import ua.com.parkhub.model.enums.RoleModel;
import ua.com.parkhub.persistence.entities.UserRole;

@Component
public class RoleModelToEntityMapper implements Mapper<RoleModel, UserRole> {

    @Override
    public UserRole transform(RoleModel from) {
        if(from == null) {
            return null;
        }
        UserRole role = new UserRole();
        role.setId(from.getId());
        role.setRoleName(from.getRoleName());
        return role;
    }
}
