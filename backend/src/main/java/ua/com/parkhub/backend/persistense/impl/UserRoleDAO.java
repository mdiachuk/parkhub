package ua.com.parkhub.backend.persistense.impl;

import ua.com.parkhub.backend.persistense.entities.UserRole;

public class UserRoleDAO extends ElementDAO<UserRole> {
    public UserRoleDAO() {
        super(UserRole.class);
    }
}
