package ua.com.parkhub.mappers.toDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.com.parkhub.dto.ParkingWithSlotsDTO;
import ua.com.parkhub.mappers.Mapper;
import ua.com.parkhub.mappers.fromModelToDTO.AddressModelToDTOMapper;
import ua.com.parkhub.model.ParkingModel;

import java.util.stream.Collectors;

@Component
public class ParkingWithSlotsModelToDTOMapper implements Mapper<ParkingModel, ParkingWithSlotsDTO> {

    private final AddressModelToDTOMapper addressModelToDTOMapper;
    private final SlotModelToDTOMapper slotModelToDTOMapper;

    @Autowired
    public ParkingWithSlotsModelToDTOMapper(AddressModelToDTOMapper addressModelToDTOMapper, SlotModelToDTOMapper slotModelToDTOMapper) {
        this.addressModelToDTOMapper = addressModelToDTOMapper;
        this.slotModelToDTOMapper = slotModelToDTOMapper;
    }

    @Override
    public ParkingWithSlotsDTO transform(ParkingModel from) {
        ParkingWithSlotsDTO parkingDTO = new ParkingWithSlotsDTO();
        parkingDTO.setId(from.getInfo().getId());
        parkingDTO.setName(from.getInfo().getParkingName());
        parkingDTO.setTariff(String.valueOf(from.getInfo().getTariff()));
        parkingDTO.setAddress(addressModelToDTOMapper.transform(from.getInfo().getAddressModel()).getAddress());
        parkingDTO.setSlots(from.getSlots().stream().map(slotModelToDTOMapper::transform).collect(Collectors.toList()));
        return parkingDTO;
    }
}
