package ua.com.parkhub.mappers.modelToEntity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
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
            return null;
        }
        UuidToken uuidToken = new UuidToken();
        uuidToken.setId(from.getId());
        uuidToken.setToken(from.getToken());
        uuidToken.setUser(userModelToEntityMapper.transform(from.getUser()));
        uuidToken.setExpirationDate(from.getExpirationDate());
        return uuidToken;
    }
}
