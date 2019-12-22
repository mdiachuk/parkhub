package ua.com.parkhub.persistence.impl;

import org.springframework.stereotype.Repository;
import ua.com.parkhub.persistence.entities.UserEntity;



@Repository
public class UserDAO extends ElementDAO<UserEntity> {

    public UserDAO() {
        super(UserEntity.class);
    }

}

