package ua.com.parkhub.mappers.modelToDto;


import org.springframework.stereotype.Component;
import ua.com.parkhub.dto.SlotDTO;
import ua.com.parkhub.mappers.Mapper;
import ua.com.parkhub.model.SlotModel;

@Component
public class SlotModelToDTOMapper implements Mapper<SlotModel, SlotDTO> {

    @Override
    public SlotDTO transform(SlotModel from) {
        if (from == null) {
            return null;
        }
        SlotDTO slotDTO = new SlotDTO();
        slotDTO.setId(from.getId());
        slotDTO.setSlotNumber(from.getSlotNumber());
        slotDTO.setReserved(from.isReserved());
        slotDTO.setActive(from.isActive());
        return slotDTO;
    }
}
