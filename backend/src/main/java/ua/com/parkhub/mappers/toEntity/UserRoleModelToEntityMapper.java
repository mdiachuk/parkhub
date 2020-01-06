package ua.com.parkhub.mappers.toEntity;

import org.springframework.stereotype.Component;
import ua.com.parkhub.exceptions.ParkHubException;
import ua.com.parkhub.mappers.Mapper;
import ua.com.parkhub.model.UserRoleModel;
import ua.com.parkhub.persistence.entities.UserRole;

@Component
public class UserRoleModelToEntityMapper implements Mapper<UserRoleModel, UserRole> {

    @Override
    public UserRole transform(UserRoleModel from) {
        if(from == null) {
            throw new ParkHubException("UserRole to be mapped to UserRoleModel entity is null.");
        }
        UserRole entity = new UserRole();
        entity.setId(from.getId());
        entity.setRoleName(from.getRoleName());
        entity.setActive(from.isActive());
        return entity;
    }
}
