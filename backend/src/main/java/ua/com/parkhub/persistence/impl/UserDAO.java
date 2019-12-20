package ua.com.parkhub.persistence.impl;

import org.springframework.stereotype.Repository;
import ua.com.parkhub.persistence.entities.User;

import java.util.Optional;

@Repository
public class UserDAO extends ElementDAO<User> {

    public UserDAO() {
        super(User.class);
    }

    public Optional<User> findUserByEmail(String email) {
        return findOneByFieldEqual("email", email);
    }
}

