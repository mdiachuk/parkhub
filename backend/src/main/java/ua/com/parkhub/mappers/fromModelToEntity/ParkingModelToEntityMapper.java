package ua.com.parkhub.mappers.fromModelToEntity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.com.parkhub.mappers.Mapper;
import ua.com.parkhub.model.ParkingModel;
import ua.com.parkhub.persistence.entities.Address;
import ua.com.parkhub.persistence.entities.Parking;
import ua.com.parkhub.persistence.impl.AddressDAO;

@Component
public class ParkingModelToEntityMapper implements Mapper<ParkingModel, Parking> {

    AddressModelToEntityMapper addressModelToEntityMapper;
    UserModelToEntityMapper userModelToEntityMapper;

    @Autowired
    public ParkingModelToEntityMapper(AddressModelToEntityMapper addressModelToEntityMapper, UserModelToEntityMapper userModelToEntityMapper) {
        this.addressModelToEntityMapper = addressModelToEntityMapper;
        this.userModelToEntityMapper = userModelToEntityMapper;
    }

    @Override
    public Parking transform(ParkingModel from) {
        Parking parking = new Parking();
        parking.setId(from.getId());
        parking.setParkingName(from.getParkingName());
        parking.setAddress(addressModelToEntityMapper.transform(from.getAddressModel()));
        parking.setTariff(from.getTariff());
        parking.setSlotsNumber(from.getSlotsNumber());
        parking.setActive(from.isActive());

        return parking;
    }
}
