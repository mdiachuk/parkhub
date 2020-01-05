package ua.com.parkhub.mappers;

import org.springframework.stereotype.Component;
import ua.com.parkhub.model.SlotModel;
import ua.com.parkhub.persistence.entities.Slot;

@Component
public class SlotModelToEntityMapper implements Mapper<SlotModel, Slot> {
    @Override
    public Slot transform(SlotModel from) {
        return null;
    }
}
