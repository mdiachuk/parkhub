package ua.com.parkhub.mappers.modelToEntity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.com.parkhub.exceptions.ParkHubException;
import ua.com.parkhub.mappers.Mapper;
import ua.com.parkhub.model.UuidTokenModel;
import ua.com.parkhub.persistence.entities.UuidToken;

@Component
public class UuidTokenModelToEntity implements Mapper<UuidTokenModel, UuidToken> {

    private UserModelToEntityMapper userModelToEntityMapper;

    @Autowired
    public UuidTokenModelToEntity(UserModelToEntityMapper userModelToEntityMapper) {
        this.userModelToEntityMapper = userModelToEntityMapper;
    }

    @Override
    public UuidToken transform(UuidTokenModel from) {
        if(from == null) {
            throw new ParkHubException("UuidToken to be mapped to UuidTokenModel entity is null.");
        }
        UuidToken entity = new UuidToken();
        entity.setId(from.getId());
        entity.setToken(from.getToken());
        entity.setUser(userModelToEntityMapper.transform(from.getUser()));
        entity.setExpirationDate(from.getExpirationDate());
        return entity;
    }
}
