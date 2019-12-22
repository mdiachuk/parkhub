package ua.com.parkhub.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import ua.com.parkhub.dto.ParkingRequestDTO;
import ua.com.parkhub.model.ParkingModel;
import ua.com.parkhub.persistence.entities.Parking;

@Mapper
public interface ParkingMapper {
    @Mappings({
            @Mapping(target = "address.city", source = "city"),
            @Mapping(target = "address.street", source = "street"),
            @Mapping(target = "address.building", source = "building"),
    })
    ParkingModel parkingRequestDTOToParkingModel(ParkingRequestDTO parkingRequestDTO);
    Parking parkingModelToParking(ParkingModel parkingModel);
}
