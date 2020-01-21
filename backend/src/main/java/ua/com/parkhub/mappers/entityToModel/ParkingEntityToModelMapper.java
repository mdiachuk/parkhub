package ua.com.parkhub.mappers.entityToModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.com.parkhub.mappers.Mapper;
import ua.com.parkhub.model.*;
import ua.com.parkhub.persistence.entities.Address;
import ua.com.parkhub.persistence.entities.Parking;
import ua.com.parkhub.persistence.entities.Slot;
import ua.com.parkhub.persistence.entities.User;

import java.util.stream.Collectors;

@Component
public class ParkingEntityToModelMapper implements Mapper<Parking, ParkingModel> {

    private Mapper<Slot, SlotModel> slotEntityToModelMapper;
    private Mapper<Address, AddressModel> addressEntityToModelMapper;
    private Mapper<User, UserModel> userEntityToModelMapper;

    @Autowired
    public ParkingEntityToModelMapper(Mapper<Slot, SlotModel> slotEntityToModelMapper, Mapper<Address, AddressModel> addressEntityToModelMapper,
                                      Mapper<User, UserModel> userEntityToModelMapper) {
        this.slotEntityToModelMapper = slotEntityToModelMapper;
        this.addressEntityToModelMapper = addressEntityToModelMapper;
        this.userEntityToModelMapper = userEntityToModelMapper;
    }

    @Override
    public ParkingModel transform(Parking from) {
        if(from == null) {
            return null;
        }
        ParkingInfoModel parkingModel = new ParkingInfoModel();
        parkingModel.setId(from.getId());
        parkingModel.setParkingName(from.getParkingName());
        parkingModel.setAddressModel(addressEntityToModelMapper.transform(from.getAddress()));
        parkingModel.setSlotsNumber(from.getSlotsNumber());
        parkingModel.setTariff(from.getTariff());
        parkingModel.setActive(true);
        parkingModel.setOwner(userEntityToModelMapper.transform(from.getOwner()));
        ParkingModel parking = new ParkingModel();
        parking.setInfo(parkingModel);
        if(from.getSlots() != null){
            parking.setSlots(from.getSlots().stream().map(slotEntityToModelMapper::transform).collect(Collectors.toList()));
        }
        return parking;
    }
}
