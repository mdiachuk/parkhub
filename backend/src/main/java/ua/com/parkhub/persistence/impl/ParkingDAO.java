package ua.com.parkhub.persistence.impl;

import org.springframework.stereotype.Repository;
import ua.com.parkhub.persistence.entities.ParkingEntity;

@Repository
public class ParkingDAO extends ElementDAO<ParkingEntity> {

    public ParkingDAO() {
        super(ParkingEntity.class);
    }
}

