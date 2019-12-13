package ua.com.parkhub.backend.persistense.impl;

import ua.com.parkhub.backend.persistense.entities.User;

public class UserDAO extends ElementDAO<User> {
    public UserDAO() {
        super(User.class);
    }
}
