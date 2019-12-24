package ua.com.parkhub.persistence.impl;

import org.springframework.stereotype.Repository;
import ua.com.parkhub.persistence.entities.UserRole;

import java.util.Optional;

@Repository
public class UserRoleDAO extends ElementDAO<UserRole> {
    public UserRoleDAO() {
        super(UserRole.class);
    }

    public Optional<UserRole> findUserRoleByRoleName(String roleName) {
        return findOneByFieldEqual("roleName", roleName);
    }
}
