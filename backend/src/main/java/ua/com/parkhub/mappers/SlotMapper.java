package ua.com.parkhub.mappers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.com.parkhub.model.SlotModel;
import ua.com.parkhub.persistence.entities.Slot;

import java.util.Optional;

@Component
public class SlotMapper implements Mapper<Slot, SlotModel> {

    private ParkingMapper parkingMapper;

    @Autowired
    public SlotMapper(ParkingMapper parkingMapper) {
        this.parkingMapper = parkingMapper;
    }

    @Override
    public Optional<Slot> toEntity(SlotModel model) {
        Slot slot = new Slot();
        slot.setParking(parkingMapper.toEntity(model.getParking()).get());
        slot.setSlotNumber(model.getSlotNumber());
        slot.setReserved(model.isReserved());
        slot.setActive(model.isActive());
        return Optional.of(slot);
    }

    @Override
    public Optional<SlotModel> toModel(Slot entity) {
        SlotModel slotModel = new SlotModel();
        slotModel.setParking(parkingMapper.toModel(entity.getParking()).get());
        slotModel.setSlotNumber(entity.getSlotNumber());
        slotModel.setReserved(entity.isReserved());
        slotModel.setActive(entity.isActive());
        return Optional.of(slotModel);
    }
}
