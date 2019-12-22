package ua.com.parkhub.mappers;

import org.springframework.stereotype.Component;
import ua.com.parkhub.dto.DescribedParkingDTO;
import ua.com.parkhub.dto.ShortParkingDTO;
import ua.com.parkhub.model.ParkingModel;
import ua.com.parkhub.persistence.entities.Parking;

import java.util.Objects;

@Component
public class ParkingMapper {

    AddressMapper addressMapper = new AddressMapper();

    public ParkingModel fromEntityToModel(Parking parking){
        return Objects.isNull(parking) ? null :
                new ParkingModel(parking.getId(), parking.getParkingName(),parking.getSlotsNumber(),parking.getTariff(),
                        addressMapper.fromEntityToModel(parking.getAddress()));
    }

    public ShortParkingDTO fromModelToShortDto(ParkingModel model) {
        return Objects.isNull(model) ? null : new ShortParkingDTO(model.getId(), model.getParkingName(),
                addressMapper.fromModelToDTO(model.getAddressModel()).getAddress());
    }

    public DescribedParkingDTO fromModelToDescribedDto(ParkingModel model) {
        return Objects.isNull(model) ? null : new DescribedParkingDTO(model.getParkingName(),
                addressMapper.fromModelToDTO(model.getAddressModel()).getAddress(), model.getSlotsNumber(), model.getTariff());
    }
}

