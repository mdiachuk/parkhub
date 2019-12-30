package ua.com.parkhub.mappers.fromModelToEntity;

import org.springframework.stereotype.Component;
import ua.com.parkhub.mappers.Mapper;
import ua.com.parkhub.model.UserRoleModel;
import ua.com.parkhub.persistence.entities.UserRole;

@Component
public class UserRoleModelToEntityMapper implements Mapper<UserRoleModel, UserRole> {
    @Override
    public UserRole transform(UserRoleModel from) {
        return null;
    }
}
