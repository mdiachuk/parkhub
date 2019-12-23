package ua.com.parkhub.persistence.impl;

import org.springframework.stereotype.Repository;
import ua.com.parkhub.persistence.entities.User;

@Repository
public class UserDAO extends ElementDAO<User> {
    public UserDAO() {
        super(User.class);
    }
}

