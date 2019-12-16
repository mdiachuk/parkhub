package ua.com.parkhub.mapper;


import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;
import ua.com.parkhub.dto.ParkingDTO;
import ua.com.parkhub.model.ParkingModel;
import ua.com.parkhub.persistence.entities.Parking;

@Mapper(componentModel = "spring")
public interface ParkingMapper {
    //ParkingMapper  INSTANCE = Mappers.getMapper( ParkingMapper.class );
    @Mappings({
            @Mapping(target = "ownerEmail", source = "owner.email"),
    })
    ParkingModel parkingToParkingModel(Parking parking);
    @Mappings({
            @Mapping(target="owner.email", source="ownerEmail"),
    })
    Parking parkingModelToParking(ParkingModel parkingModel);
    ParkingDTO parkingModelToParkingDTO(ParkingModel parkingModel);
    ParkingModel parkingDTOToParkingModel(ParkingDTO parkingDTO);
}
