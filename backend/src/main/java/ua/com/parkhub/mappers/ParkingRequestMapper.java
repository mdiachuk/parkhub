package ua.com.parkhub.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import ua.com.parkhub.dto.ParkingRequestDTO;
import ua.com.parkhub.model.ParkingModel;
import ua.com.parkhub.persistence.entities.Address;
import ua.com.parkhub.persistence.entities.Parking;

@Mapper
public interface ParkingRequestMapper {
   /* @Mappings({
            @Mapping(target = "addressModel.city", source = "city"),
            @Mapping(target = "addressModel.street", source = "street"),
            @Mapping(target = "addressModel.building", source = "building"),
    })
    ParkingModel parkingRequestDTOToParkingModel(ParkingRequestDTO parkingRequestDTO);
    @Mappings({
            @Mapping(target = "id", ignore = true)
    })
    Parking parkingModelToParking(ParkingModel parkingModel);
    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "city", source = "addressModel.city"),
            @Mapping(target = "street", source = "addressModel.street"),
            @Mapping(target = "building", source = "addressModel.building"),
    })
    Address parkingModelToAddress(ParkingModel parkingModel);*/
}
