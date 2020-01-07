package ua.com.parkhub.mappers.ModelToEntity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.com.parkhub.mappers.EntityToModel.AddressEntityToModelMapper;
import ua.com.parkhub.mappers.EntityToModel.SlotEntityToModelMapper;
import ua.com.parkhub.mappers.EntityToModel.UserEntityToModelMapper;
import ua.com.parkhub.mappers.Mapper;
import ua.com.parkhub.model.ParkingModel;
import ua.com.parkhub.persistence.entities.Parking;
import ua.com.parkhub.persistence.entities.User;

@Component
public class ParkingModelToEntityMapper implements Mapper<ParkingModel, Parking> {

    private AddressModelToEntityMapper addressModelToEntityMapper;
    private UserModelToEntityMapper userModelToEntityMapper;

    @Autowired
    public ParkingModelToEntityMapper(AddressModelToEntityMapper addressModelToEntityMapper, UserModelToEntityMapper userModelToEntityMapper) {
        this.addressModelToEntityMapper = addressModelToEntityMapper;
        this.userModelToEntityMapper = userModelToEntityMapper;
    }

    @Override
    public Parking transform(ParkingModel from) {
        Parking parking = new Parking();
        parking.setId(from.getId());
        parking.setTariff(from.getTariff());
        parking.setParkingName(from.getParkingName());
        parking.setSlotsNumber(from.getSlotsNumber());
        parking.setOwner(userModelToEntityMapper.transform(from.getOwner()));
        parking.setAddress(addressModelToEntityMapper.transform(from.getAddressModel()));
        return parking;

    }
}
