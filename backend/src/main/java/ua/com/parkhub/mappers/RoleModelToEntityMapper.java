package ua.com.parkhub.mappers;

import org.springframework.stereotype.Component;
import ua.com.parkhub.model.RoleModel;
import ua.com.parkhub.persistence.entities.UserRole;

@Component
public class RoleModelToEntityMapper implements Mapper<RoleModel,ua.com.parkhub.persistence.entities.UserRole> {

    @Override
    public ua.com.parkhub.persistence.entities.UserRole transform(RoleModel model) {
        UserRole role = new UserRole();
        role.setRoleName(model.getRoleName());
        return role;
    }
}
