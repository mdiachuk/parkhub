package ua.com.parkhub.persistence.impl;

import org.springframework.stereotype.Repository;
import ua.com.parkhub.mappers.Mapper;
import ua.com.parkhub.model.UuidTokenModel;
import ua.com.parkhub.persistence.entities.UuidToken;

import java.util.Optional;

@Repository
public class UuidTokenDAO extends ElementDAO<UuidToken, UuidTokenModel> {

    public UuidTokenDAO(Mapper<UuidToken, UuidTokenModel> entityToModel, Mapper<UuidTokenModel, UuidToken> modelToEntity) {
        super(UuidToken.class, modelToEntity, entityToModel);
    }

    public Optional<UuidTokenModel> findUuidTokenByToken(String token) {
        return findOneByFieldEqual("token", token);
    }
}