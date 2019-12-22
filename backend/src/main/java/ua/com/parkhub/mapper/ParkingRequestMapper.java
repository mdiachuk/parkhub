package ua.com.parkhub.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import ua.com.parkhub.dto.ParkingRequestDTO;
import ua.com.parkhub.model.ParkingModel;
import ua.com.parkhub.persistence.entities.Address;
import ua.com.parkhub.persistence.entities.Parking;

@Mapper
public interface ParkingRequestMapper {
    @Mappings({
            @Mapping(target = "address.city", source = "city"),
            @Mapping(target = "address.street", source = "street"),
            @Mapping(target = "address.building", source = "building"),
    })
    ParkingModel parkingRequestDTOToParkingModel(ParkingRequestDTO parkingRequestDTO);
    Parking parkingModelToParking(ParkingModel parkingModel);
    @Mappings({
            @Mapping(target = "city", source = "address.city"),
            @Mapping(target = "street", source = "address.street"),
            @Mapping(target = "building", source = "address.building"),
    })
    Address parkingModelToAddress(ParkingModel parkingModel);
}
