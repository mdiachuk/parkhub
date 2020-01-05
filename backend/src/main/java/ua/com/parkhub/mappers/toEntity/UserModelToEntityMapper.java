package ua.com.parkhub.mappers.toEntity;

import org.springframework.stereotype.Component;
import ua.com.parkhub.mappers.Mapper;
import ua.com.parkhub.model.UserModel;
import ua.com.parkhub.persistence.entities.User;

@Component
public class UserModelToEntityMapper implements Mapper<UserModel, User> {
    @Override
    public User transform(UserModel from) {
        return null;
    }
}
