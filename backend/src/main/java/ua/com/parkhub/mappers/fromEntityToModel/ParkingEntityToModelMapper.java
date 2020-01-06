package ua.com.parkhub.mappers.fromEntityToModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.com.parkhub.mappers.Mapper;
import ua.com.parkhub.model.ParkingInfoModel;
import ua.com.parkhub.model.ParkingModel;
import ua.com.parkhub.model.SlotModel;
import ua.com.parkhub.persistence.entities.Parking;

import java.util.List;
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
        ParkingInfoModel parkingInfoModel = new ParkingInfoModel();
        parkingInfoModel.setId(from.getId());
        parkingInfoModel.setParkingName(from.getParkingName());
        parkingInfoModel.setAddressModel(addressEntityToModelMapper.transform(from.getAddress()));
        parkingInfoModel.setSlotsNumber(from.getSlotsNumber());
        parkingInfoModel.setTariff(from.getTariff());
        parkingInfoModel.setActive(from.isActive());
        List<SlotModel> slots = from.getSlots().stream().map(slotEntityToModelMapper::transform).collect(Collectors.toList());
//        parkingModel.setOwner(userEntityToModelMapper.transform(from.getOwner()));
        ParkingModel parkingModel = new ParkingModel();
        parkingModel.setSlots(slots);
        parkingModel.setInfo(parkingInfoModel);
        return parkingModel;
    }
}
