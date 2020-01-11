package ua.com.parkhub.mappers.modelToEntity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.com.parkhub.mappers.Mapper;
import ua.com.parkhub.model.ParkingModel;
import ua.com.parkhub.persistence.entities.Parking;


import java.util.stream.Collectors;

@Component
public class ParkingModelToEntityMapper implements Mapper<ParkingModel, Parking> {

    private AddressModelToEntityMapper addressModelToEntityMapper;
    private UserModelToEntityMapper userModelToEntityMapper;
    private SlotModelToEntityMapper slotModelToEntityMapper;

    @Autowired
    public ParkingModelToEntityMapper(AddressModelToEntityMapper addressModelToEntityMapper, UserModelToEntityMapper userModelToEntityMapper, SlotModelToEntityMapper slotModelToEntityMapper) {
        this.addressModelToEntityMapper = addressModelToEntityMapper;
        this.userModelToEntityMapper = userModelToEntityMapper;
        this.slotModelToEntityMapper = slotModelToEntityMapper;
    }

    @Override
    public Parking transform(ParkingModel from) {
        if(from == null) {
            return null;
        }
        Parking parking = new Parking();
        parking.setId(from.getId());
        parking.setParkingName(from.getParkingName());
        parking.setAddress(addressModelToEntityMapper.transform(from.getAddressModel()));
        parking.setSlotsNumber(from.getSlotsNumber());
        parking.setTariff(from.getTariff());
        parking.setActive(from.isActive());
        if(from.getSlots() != null){
            parking.setSlots(from.getSlots().stream().map(slotModelToEntityMapper::transform).collect(Collectors.toList()));
        }
        parking.setOwner(userModelToEntityMapper.transform(from.getOwner()));
        return parking;

    }
}
