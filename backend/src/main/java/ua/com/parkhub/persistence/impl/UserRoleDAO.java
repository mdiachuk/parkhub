package ua.com.parkhub.persistence.impl;

import org.springframework.stereotype.Repository;
import ua.com.parkhub.persistence.entities.UserRoleEntity;

@Repository
public class UserRoleDAO extends ElementDAO<UserRoleEntity> {

    public UserRoleDAO() {
        super(UserRoleEntity.class);
    }
}

