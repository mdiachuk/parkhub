package ua.com.parkhub.mappers.entityToModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.com.parkhub.mappers.Mapper;
import ua.com.parkhub.model.UserModel;
import ua.com.parkhub.model.UuidTokenModel;
import ua.com.parkhub.persistence.entities.User;
import ua.com.parkhub.persistence.entities.UuidToken;

@Component
public class UuidTokenEntityToModelMapper implements Mapper<UuidToken, UuidTokenModel> {

    private Mapper<User, UserModel> userEntityToModelMapper;

    @Autowired
    public UuidTokenEntityToModelMapper(Mapper<User, UserModel> userEntityToModelMapper) {
        this.userEntityToModelMapper = userEntityToModelMapper;
    }

    @Override
    public UuidTokenModel transform(UuidToken from) {
        if (from == null) {
            return null;
        }
        UuidTokenModel uuidTokenModel = new UuidTokenModel();
        uuidTokenModel.setId(from.getId());
        uuidTokenModel.setToken(from.getToken());
        uuidTokenModel.setUser(userEntityToModelMapper.transform(from.getUser()));
        uuidTokenModel.setExpirationDate(from.getExpirationDate());
        return uuidTokenModel;
    }
}
