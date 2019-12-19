package ua.com.parkhub.mappers;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import ua.com.parkhub.dto.ParkingDTO;
import ua.com.parkhub.model.ParkingModel;
import ua.com.parkhub.persistence.entities.Parking;

import java.util.Objects;

@Component
public class ParkingMapper {

    AddressMapper addressMapper = new AddressMapper();

    public ParkingModel fromEntityToModel(Parking parking){
        return Objects.isNull(parking) ? null :
                new ParkingModel(parking.getParkingName(),parking.getSlotsNumber(),parking.getTariff(),parking.isActive(),parking.getOwner(),
                        addressMapper.fromEntityToModel(parking.getAddress()));
    }

    public ParkingDTO fromModelToDto(ParkingModel model) {
        return Objects.isNull(model) ? null : new ParkingDTO(model.getParkingName(),
                addressMapper.fromModelToDTO(model.getAddressModel()).getAddress());
    }

    /*public Parking fromDtoToModel(ParkingDTO dto) {

    }*/

//    public Parking fromModeltoEntity(ParkingModel model) {
//
//    }
}

