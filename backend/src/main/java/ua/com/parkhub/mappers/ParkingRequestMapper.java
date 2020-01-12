package ua.com.parkhub.mappers;

import org.mapstruct.Mapper;

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
