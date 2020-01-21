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
public class SlotModelToEntityMapper implements Mapper<SlotModel, Slot> {



    @Override
    public Slot transform(SlotModel from) {
        if (from == null) {
            return null;
        }
        Slot slot = new Slot();
        slot.setId(from.getId());
        slot.setSlotNumber(from.getSlotNumber());
        slot.setActive(from.isActive());
        slot.setReserved(from.isReserved());
        return slot;
    }
}
