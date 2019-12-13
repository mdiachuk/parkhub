package ua.com.parkhub.backend.persistense.impl;

import ua.com.parkhub.backend.persistense.entities.Parking;

public class ParkingDAO extends ElementDAO<Parking> {
    public ParkingDAO() {
        super(Parking.class);
    }
}
