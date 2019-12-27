package ua.com.parkhub.persistence.impl;

import org.springframework.stereotype.Repository;
import ua.com.parkhub.persistence.entities.UuidToken;

import java.util.Optional;

@Repository
public class UuidTokenDAO extends ElementDAO<UuidToken> {

    public UuidTokenDAO() {
        super(UuidToken.class);
    }

    public Optional<UuidToken> findUuidTokenByToken(String token) {
        return findOneByFieldEqual("token", token);
    }
}