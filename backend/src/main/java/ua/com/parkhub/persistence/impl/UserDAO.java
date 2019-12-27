package ua.com.parkhub.persistence.impl;

import org.springframework.stereotype.Repository;
import ua.com.parkhub.mappers.Mapper;
import ua.com.parkhub.model.ParkingModel;
import ua.com.parkhub.model.UserModel;
import ua.com.parkhub.persistence.entities.Parking;
import ua.com.parkhub.persistence.entities.User;

@Repository
public class UserDAO extends ElementDAO<User, UserModel> {

    public UserDAO(Mapper<User, UserModel> entityToModel, Mapper<UserModel, User> modelToEntity) {
        super(User.class, modelToEntity, entityToModel);
    }
}

