package ua.com.parkhub.mappers;

import org.springframework.stereotype.Component;
import ua.com.parkhub.model.UserRoleModel;
import ua.com.parkhub.persistence.entities.UserRole;

import java.util.Optional;

@Component
public class UserRoleMapper implements Mapper<UserRole, UserRoleModel> {

    @Override
    public Optional<UserRole> toEntity(UserRoleModel model) {
        return Optional.of(new UserRole(model.getRoleName(),
                model.isActive()));
    }

    @Override
    public Optional<UserRoleModel> toModel(UserRole entity) {
        return Optional.of(new UserRoleModel(entity.getRoleName(),
                entity.isActive()));
    }
}
