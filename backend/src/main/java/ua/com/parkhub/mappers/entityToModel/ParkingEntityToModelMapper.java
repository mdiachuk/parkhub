package ua.com.parkhub.mappers.entityToModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.com.parkhub.mappers.Mapper;
import ua.com.parkhub.model.AddressModel;
import ua.com.parkhub.model.ParkingModel;
import ua.com.parkhub.model.SlotModel;
import ua.com.parkhub.model.UserModel;
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
        ParkingModel parkingModel = new ParkingModel();
        parkingModel.setId(from.getId());
        parkingModel.setParkingName(from.getParkingName());
        parkingModel.setAddressModel(addressEntityToModelMapper.transform(from.getAddress()));
        parkingModel.setSlotsNumber(from.getSlotsNumber());
        parkingModel.setTariff(from.getTariff());
        parkingModel.setActive(from.isActive());
        if(from.getSlots() != null){
            parkingModel.setSlots(from.getSlots().stream().map(slotEntityToModelMapper::transform).collect(Collectors.toList()));
        }
        parkingModel.setOwner(userEntityToModelMapper.transform(from.getOwner()));
        return parkingModel;
    }
}
