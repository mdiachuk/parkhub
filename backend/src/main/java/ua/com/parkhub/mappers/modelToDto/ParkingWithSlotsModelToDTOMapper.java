package ua.com.parkhub.mappers.modelToDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.com.parkhub.dto.AddressDTO;
import ua.com.parkhub.dto.ParkingWithSlotsDTO;
import ua.com.parkhub.dto.SlotDTO;
import ua.com.parkhub.mappers.Mapper;
import ua.com.parkhub.model.AddressModel;
import ua.com.parkhub.model.ParkingModel;
import ua.com.parkhub.model.SlotModel;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ParkingWithSlotsModelToDTOMapper implements Mapper<ParkingModel, ParkingWithSlotsDTO> {

    private final Mapper<AddressModel, AddressDTO> addressModelToDTOMapper;
    private final Mapper<SlotModel, SlotDTO> slotModelToDTOMapper;

    @Autowired
    public ParkingWithSlotsModelToDTOMapper(Mapper<AddressModel, AddressDTO> addressModelToDTOMapper,
                                            Mapper<SlotModel, SlotDTO> slotModelToDTOMapper) {
        this.addressModelToDTOMapper = addressModelToDTOMapper;
        this.slotModelToDTOMapper = slotModelToDTOMapper;
    }

    @Override
    public ParkingWithSlotsDTO transform(ParkingModel from) {
        if (from == null) {
            return null;
        }
        ParkingWithSlotsDTO parkingDTO = new ParkingWithSlotsDTO();
        parkingDTO.setId(from.getInfo().getId());
        parkingDTO.setName(from.getInfo().getParkingName());
        parkingDTO.setTariff(String.valueOf(from.getInfo().getTariff()));
        parkingDTO.setAddress(addressModelToDTOMapper.transform(from.getInfo().getAddressModel()).getAddress());
        if (from.getSlots() != null) {
            List<SlotDTO> slots = from.getSlots().stream().map(slotModelToDTOMapper::transform).collect(Collectors.toList());
            parkingDTO.setSlots(slots);
        }
        return parkingDTO;
    }
}
