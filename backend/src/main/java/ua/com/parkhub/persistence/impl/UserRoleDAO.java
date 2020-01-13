package ua.com.parkhub.persistence.impl;

import org.springframework.stereotype.Repository;

import ua.com.parkhub.mappers.Mapper;
import ua.com.parkhub.model.enums.RoleModel;
import ua.com.parkhub.persistence.entities.UserRole;

import java.util.Optional;

@Repository
public class UserRoleDAO extends ElementDAO<UserRole, RoleModel> {

    public UserRoleDAO(Mapper<UserRole, RoleModel> entityToModel, Mapper<RoleModel, UserRole> modelToEntity) {
        super(UserRole.class, modelToEntity, entityToModel);
    }

    public Optional<RoleModel> findUserRoleByRoleName(String roleName) {
        return findOneByFieldEqual("roleName", roleName);
    }
}

