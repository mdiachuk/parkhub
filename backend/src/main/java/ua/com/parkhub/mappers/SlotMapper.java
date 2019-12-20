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
        return Optional.of(new Slot(model.getSlotNumber(),
                model.isReserved(),
                model.isActive(),
                parkingMapper.toEntity(model.getParking()).get()));
    }

    @Override
    public Optional<SlotModel> toModel(Slot entity) {
        return Optional.of(new SlotModel(parkingMapper.toModel(entity.getParking()).get(),
                entity.getSlotNumber(),
                entity.isReserved(),
                entity.isActive()));
    }
}
