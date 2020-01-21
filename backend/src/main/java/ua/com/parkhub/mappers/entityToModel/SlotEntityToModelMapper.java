package ua.com.parkhub.mappers.entityToModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.com.parkhub.mappers.Mapper;
import ua.com.parkhub.mappers.modelToEntity.ParkingModelToEntityMapper;
import ua.com.parkhub.model.CustomerModel;
import ua.com.parkhub.model.ParkingModel;
import ua.com.parkhub.model.SlotModel;
import ua.com.parkhub.persistence.entities.Customer;
import ua.com.parkhub.persistence.entities.Parking;
import ua.com.parkhub.persistence.entities.Slot;

@Component
public class SlotEntityToModelMapper implements Mapper<Slot, SlotModel> {

    @Override
    public SlotModel transform(Slot from) {
        if(from == null) {
            return null;
        }
        SlotModel slotModel = new SlotModel();
        slotModel.setId(from.getId());
        slotModel.setSlotNumber(from.getSlotNumber());
        slotModel.setActive(from.isActive());
        slotModel.setReserved(from.isReserved());
        return slotModel;
    }
}
