package ua.com.parkhub.mappers.toModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.com.parkhub.mappers.Mapper;
import ua.com.parkhub.model.SlotModel;
import ua.com.parkhub.persistence.entities.Slot;

@Component
public class SlotEntityToModelMapper implements Mapper<Slot, SlotModel> {

    ParkingEntityToModelMapper parkingEntityToModelMapper;

    @Autowired
    public SlotEntityToModelMapper(ParkingEntityToModelMapper parkingEntityToModelMapper) {
        this.parkingEntityToModelMapper = parkingEntityToModelMapper;
    }

    @Override
    public SlotModel transform(Slot from) {
        SlotModel slotModel = new SlotModel();
        slotModel.setSlotNumber(from.getSlotNumber());
        slotModel.setActive(from.isActive());
        slotModel.setReserved(from.isReserved());
        slotModel.setParking(parkingEntityToModelMapper.transform(from.getParking()));
        return slotModel;
    }
}
