package ua.com.parkhub.persistence.impl;

import org.springframework.stereotype.Repository;
import ua.com.parkhub.persistence.entities.Parking;

@Repository
public class ParkingDAO extends ElementDAO<Parking> {

    public ParkingDAO() {
        super(Parking.class);
    }


}

