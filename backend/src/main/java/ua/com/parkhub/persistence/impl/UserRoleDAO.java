package ua.com.parkhub.persistence.impl;

import org.springframework.stereotype.Repository;
import ua.com.parkhub.persistence.entities.UserRole;

@Repository
public class UserRoleDAO extends ElementDAO<UserRole> {

    public UserRoleDAO() {
        super(UserRole.class);
    }
}

