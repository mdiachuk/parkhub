package ua.com.parkhub.mappers.entityToModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.com.parkhub.mappers.Mapper;
import ua.com.parkhub.model.UuidTokenModel;
import ua.com.parkhub.persistence.entities.UuidToken;

@Component
public class UuidTokenEntityToModelMapper implements Mapper<UuidToken, UuidTokenModel> {

    private UserEntityToModelMapper userEntityToModelMapper;

    @Autowired
    public UuidTokenEntityToModelMapper(UserEntityToModelMapper userEntityToModelMapper) {
        this.userEntityToModelMapper = userEntityToModelMapper;
    }

    @Override
    public UuidTokenModel transform(UuidToken from) {
        if (from == null) {
            return null;
        }
        UuidTokenModel model = new UuidTokenModel();
        model.setId(from.getId());
        model.setToken(from.getToken());
        model.setUser(userEntityToModelMapper.transform(from.getUser()));
        model.setExpirationDate(from.getExpirationDate());
        return model;
    }
}
