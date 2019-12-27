package ua.com.parkhub.persistence.impl;

import org.springframework.stereotype.Repository;
import ua.com.parkhub.mappers.Mapper;
import ua.com.parkhub.model.AddressModel;
import ua.com.parkhub.model.ParkingModel;
import ua.com.parkhub.persistence.entities.Address;
import ua.com.parkhub.persistence.entities.Parking;

@Repository
public class ParkingDAO extends ElementDAO<Parking, ParkingModel> {

    public ParkingDAO(Mapper<Parking, ParkingModel> entityToModel, Mapper<ParkingModel, Parking> modelToEntity) {
        super(Parking.class, modelToEntity, entityToModel);
    }
}

