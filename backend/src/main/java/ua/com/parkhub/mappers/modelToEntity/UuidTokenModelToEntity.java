package ua.com.parkhub.mappers.modelToEntity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.com.parkhub.mappers.Mapper;
import ua.com.parkhub.model.UserModel;
import ua.com.parkhub.model.UuidTokenModel;
import ua.com.parkhub.persistence.entities.User;
import ua.com.parkhub.persistence.entities.UuidToken;

@Component
public class UuidTokenModelToEntity implements Mapper<UuidTokenModel, UuidToken> {

    private Mapper<UserModel, User> userModelToEntityMapper;

    @Autowired
    public UuidTokenModelToEntity(Mapper<UserModel, User> userModelToEntityMapper) {
        this.userModelToEntityMapper = userModelToEntityMapper;
    }

    @Override
    public UuidToken transform(UuidTokenModel from) {
        if(from == null) {
            return null;
        }
        UuidToken entity = new UuidToken();
        entity.setId(from.getId());
        entity.setToken(from.getToken());
        entity.setUser(userModelToEntityMapper.transform(from.getUser()));
        entity.setExpirationDate(from.getExpirationDate());
        return entity;
    }
}
