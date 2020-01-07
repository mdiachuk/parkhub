package ua.com.parkhub.mappers.toModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.com.parkhub.mappers.Mapper;
import ua.com.parkhub.model.ParkingModel;
import ua.com.parkhub.persistence.entities.Parking;

import java.util.stream.Collectors;

@Component
public class ParkingEntityToModelMapper implements Mapper<Parking, ParkingModel> {

    SlotEntityToModelMapper slotEntityToModelMapper;
    AddressEntityToModelMapper addressEntityToModelMapper;
    UserEntityToModelMapper userEntityToModelMapper;

    @Autowired
    public ParkingEntityToModelMapper(SlotEntityToModelMapper slotEntityToModelMapper, AddressEntityToModelMapper addressEntityToModelMapper, UserEntityToModelMapper userEntityToModelMapper) {
        this.slotEntityToModelMapper = slotEntityToModelMapper;
        this.addressEntityToModelMapper = addressEntityToModelMapper;
        this.userEntityToModelMapper = userEntityToModelMapper;
    }

    @Override
    public ParkingModel transform(Parking from) {
        ParkingModel parkingModel = new ParkingModel();
        parkingModel.setId(from.getId());
        parkingModel.setParkingName(from.getParkingName());
        parkingModel.setAddressModel(addressEntityToModelMapper.transform(from.getAddress()));
        parkingModel.setSlotsNumber(from.getSlotsNumber());
        parkingModel.setTariff(from.getTariff());
        parkingModel.setActive(from.isActive());
        parkingModel.setSlots(from.getSlots().stream().map(slotEntityToModelMapper::transform).collect(Collectors.toList()));
//        parkingModel.setOwner(userEntityToModelMapper.transform(from.getOwner()));
        return parkingModel;
    }
}
