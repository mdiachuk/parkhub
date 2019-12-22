package ua.com.parkhub.mappers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.com.parkhub.model.ParkingModel;
import ua.com.parkhub.persistence.entities.Parking;

import java.util.Optional;

@Component
public class ParkingMapper implements Mapper<Parking, ParkingModel> {

    private UserMapper userMapper;
    private AddressMapper addressMapper;

    @Autowired
    public ParkingMapper(UserMapper userMapper, AddressMapper addressMapper) {
        this.userMapper = userMapper;
        this.addressMapper = addressMapper;
    }

    @Override
    public Optional<Parking> toEntity(ParkingModel model) {
        Parking parking = new Parking();
        parking.setParkingName(model.getParkingName());
        parking.setAddress(addressMapper.toEntity(model.getAddress()).get());
        parking.setSlotsNumber(model.getSlotsNumber());
        parking.setOwner(userMapper.toEntity(model.getUser()).get());
        parking.setTariff(model.getTariff());
        parking.setActive(model.isActive());
        return Optional.of(parking);
    }

    @Override
    public Optional<ParkingModel> toModel(Parking entity) {
        ParkingModel parkingModel = new ParkingModel();
        parkingModel.setParkingName(entity.getParkingName());
        parkingModel.setUser(userMapper.toModel(entity.getOwner()).get());
        parkingModel.setAddress(addressMapper.toModel(entity.getAddress()).get());
        parkingModel.setSlotsNumber(entity.getSlotsNumber());
        parkingModel.setTariff(entity.getTariff());
        parkingModel.setActive(entity.isActive());
        return Optional.of(parkingModel);
    }
}
