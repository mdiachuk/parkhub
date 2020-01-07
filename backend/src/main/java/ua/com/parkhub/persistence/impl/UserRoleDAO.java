package ua.com.parkhub.persistence.impl;

import org.springframework.stereotype.Repository;
import ua.com.parkhub.mappers.Mapper;
import ua.com.parkhub.model.RoleModel;
import ua.com.parkhub.persistence.entities.UserRole;

@Repository
public class UserRoleDAO extends ElementDAO<UserRole, RoleModel> {

    public UserRoleDAO(Mapper<UserRole, RoleModel> entityToModel, Mapper<RoleModel, UserRole> modelToEntity) {
        super(UserRole.class, modelToEntity, entityToModel);
    }
}

