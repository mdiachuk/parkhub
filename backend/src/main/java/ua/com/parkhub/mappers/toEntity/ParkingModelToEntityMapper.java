package ua.com.parkhub.mappers.toEntity;

import org.springframework.stereotype.Component;
import ua.com.parkhub.mappers.Mapper;
import ua.com.parkhub.model.ParkingModel;
import ua.com.parkhub.persistence.entities.Parking;

@Component
public class ParkingModelToEntityMapper implements Mapper<ParkingModel, Parking> {
    @Override
    public Parking transform(ParkingModel from) {
        return null;
    }
}