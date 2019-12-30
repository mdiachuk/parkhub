package ua.com.parkhub.persistence.impl;

import org.springframework.stereotype.Repository;
import ua.com.parkhub.persistence.entities.UuidTokenType;

import java.util.Optional;

@Repository
public class UuidTokenTypeDAO extends ElementDAO<UuidTokenType> {

    public UuidTokenTypeDAO() {
        super(UuidTokenType.class);
    }

    public Optional<UuidTokenType> findUuidTokenTypeByType(String type) {
        return findOneByFieldEqual("type", type);
    }
}
