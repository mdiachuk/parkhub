package ua.com.parkhub.mappers.modelToDto;

import org.springframework.stereotype.Component;
import ua.com.parkhub.dto.ParkingDTO;
import ua.com.parkhub.mappers.Mapper;
import ua.com.parkhub.model.ParkingModel;
import ua.com.parkhub.model.SlotModel;

@Component
public class ParkingModelToDTOMapper implements Mapper<ParkingModel, ParkingDTO> {

    @Override
    public ParkingDTO transform(ParkingModel from) {
        ParkingDTO parkingDTO = new ParkingDTO();
        parkingDTO.setId(from.getInfo().getId());
        parkingDTO.setParkingName(from.getInfo().getParkingName());
        parkingDTO.setSlotsNumber(from.getInfo().getSlotsNumber());
        parkingDTO.setTariff(from.getInfo().getTariff());
        parkingDTO.setAddress(new AddressModelToDTOMapper().transform(from.getInfo().getAddressModel()).getAddress());//get string address from AddressDTO
        parkingDTO.setFullness(from.getSlots().stream().filter(SlotModel::isReserved).count()+ "/" + from.getInfo().getSlotsNumber());
        return parkingDTO;
    }
}
