package ua.com.parkhub.mappers.toModel;

import org.springframework.stereotype.Component;
import ua.com.parkhub.model.Mapper;
import ua.com.parkhub.model.RoleModel;
import ua.com.parkhub.persistence.entities.UserRole;

@Component
public class UserRoleEntityToModelMapper implements Mapper<UserRole, RoleModel> {
    @Override
    public RoleModel transform(UserRole from) {

        return null;
    }
}
