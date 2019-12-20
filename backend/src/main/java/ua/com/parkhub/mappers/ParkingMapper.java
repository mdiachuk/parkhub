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
        return Optional.of(new Parking(model.getParkingName(),
                model.getSlotsNumber(),
                model.getTariff(),
                model.isActive(),
                addressMapper.toEntity(model.getAddress()).get(),
                userMapper.toEntity(model.getUser()).get()));
    }

    @Override
    public Optional<ParkingModel> toModel(Parking entity) {
        return Optional.of(new ParkingModel(
                userMapper.toModel(entity.getOwner()).get(),
                entity.getParkingName(),
                addressMapper.toModel(entity.getAddress()).get(),
                entity.getSlotsNumber(),
                entity.getTariff(),
                entity.isActive()));
    }
}
