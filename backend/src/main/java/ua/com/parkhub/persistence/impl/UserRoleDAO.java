package ua.com.parkhub.persistence.impl;

import org.springframework.stereotype.Repository;
import ua.com.parkhub.mappers.Mapper;
import ua.com.parkhub.model.ParkingModel;
import ua.com.parkhub.model.UserRoleModel;
import ua.com.parkhub.persistence.entities.Parking;
import ua.com.parkhub.persistence.entities.UserRole;

import java.util.Optional;

@Repository
public class UserRoleDAO extends ElementDAO<UserRole, UserRoleModel> {

    public UserRoleDAO(Mapper<UserRole, UserRoleModel> entityToModel, Mapper<UserRoleModel, UserRole> modelToEntity) {
        super(UserRole.class, modelToEntity, entityToModel);
    }

    public Optional<UserRole> findUserRoleByRoleName(String roleName) {
        return findOneByFieldEqual("roleName", roleName);
    }
}

