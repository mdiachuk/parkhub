package ua.com.parkhub.mappers.modelToEntity;

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
public class ParkingModelToEntityMapper implements Mapper<ParkingModel, Parking> {

    private Mapper<AddressModel, Address> addressModelToEntityMapper;
    private Mapper<UserModel, User> userModelToEntityMapper;
    private Mapper<SlotModel, Slot> slotModelToEntityMapper;

    @Autowired
    public ParkingModelToEntityMapper(Mapper<AddressModel, Address> addressModelToEntityMapper,
                                      Mapper<UserModel, User> userModelToEntityMapper,
                                      Mapper<SlotModel, Slot> slotModelToEntityMapper) {
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
        parking.setId(from.getInfo().getId());
        parking.setParkingName(from.getInfo().getParkingName());
        parking.setAddress(addressModelToEntityMapper.transform(from.getInfo().getAddressModel()));
        parking.setSlotsNumber(from.getInfo().getSlotsNumber());
        parking.setTariff(from.getInfo().getTariff());
        parking.setActive(true);
        parking.setOwner(userModelToEntityMapper.transform(from.getInfo().getOwner()));
        if(from.getSlots() != null){
            parking.setSlots(from.getSlots().stream().map(slotModelToEntityMapper::transform).collect(Collectors.toList()));
            parking.getSlots().forEach(slot -> slot.setParking(parking));
        }
        return parking;

    }
}
