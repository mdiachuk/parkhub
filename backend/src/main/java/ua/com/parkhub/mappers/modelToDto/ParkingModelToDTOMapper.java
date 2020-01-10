package ua.com.parkhub.mappers.modelToDTO;

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
        parkingDTO.setId(from.getId());
        parkingDTO.setParkingName(from.getParkingName());
        parkingDTO.setSlotsNumber(from.getSlotsNumber());
        parkingDTO.setTariff(from.getTariff());
        parkingDTO.setAddress(new AddressModelToDTOMapper().transform(from.getAddressModel()).getAddress());//get string address from AddressDTO
        parkingDTO.setFullness(from.getSlots().stream().filter(SlotModel::isReserved).count()+ "/" + from.getSlotsNumber());
        return parkingDTO;
        }
    }

