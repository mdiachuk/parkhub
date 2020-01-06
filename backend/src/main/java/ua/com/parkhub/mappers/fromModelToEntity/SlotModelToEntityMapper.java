package ua.com.parkhub.mappers.fromModelToEntity;

import org.springframework.stereotype.Component;
import ua.com.parkhub.mappers.Mapper;
import ua.com.parkhub.model.SlotModel;
import ua.com.parkhub.persistence.entities.Slot;

@Component
public class SlotModelToEntityMapper implements Mapper<SlotModel, Slot> {

    @Override
    public Slot transform(SlotModel from) {
        Slot slot = new Slot();
        slot.setId(from.getId());
        slot.setSlotNumber(from.getSlotNumber());
        slot.setActive(from.isActive());
        slot.setReserved(from.isReserved());
        return slot;
    }
}
