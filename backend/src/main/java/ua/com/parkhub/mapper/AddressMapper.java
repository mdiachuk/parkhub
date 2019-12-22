package ua.com.parkhub.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import ua.com.parkhub.model.ParkingModel;
import ua.com.parkhub.persistence.entities.Address;

@Mapper
public interface AddressMapper {

    @Mappings({
            @Mapping(target = "city", source = "address.city"),
            @Mapping(target = "street", source = "address.street"),
            @Mapping(target = "building", source = "address.building"),
    })
    Address parkingModelToAddress(ParkingModel parkingModel);
}
