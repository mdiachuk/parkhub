package ua.com.parkhub.mappers.fromEntityToModel;

import org.springframework.stereotype.Component;
import ua.com.parkhub.mappers.Mapper;
import ua.com.parkhub.model.SlotModel;
import ua.com.parkhub.persistence.entities.Slot;

@Component
public class SlotEntityToModelMapper implements Mapper<Slot, SlotModel> {

    @Override
    public SlotModel transform(Slot from) {
        SlotModel slotModel = new SlotModel();
        slotModel.setId(from.getId());
        slotModel.setSlotNumber(from.getSlotNumber());
        slotModel.setActive(from.isActive());
        slotModel.setReserved(from.isReserved());
        return slotModel;
    }
}
