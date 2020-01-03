package ua.com.parkhub.mappers.toEntity;

import org.springframework.stereotype.Component;
import ua.com.parkhub.model.Mapper;
import ua.com.parkhub.model.RoleModel;
import ua.com.parkhub.persistence.entities.UserRole;

@Component
public class UserRoleModelToEntityMapper implements Mapper<RoleModel, UserRole> {
    @Override
    public UserRole transform(RoleModel from) {
        return null;
    }
}
