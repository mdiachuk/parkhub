package ua.com.parkhub.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;
import ua.com.parkhub.dto.AddressDTO;
import ua.com.parkhub.dto.ParkingDTO;
import ua.com.parkhub.model.AddressModel;
import ua.com.parkhub.model.ParkingModel;
import ua.com.parkhub.persistence.entities.Address;
import ua.com.parkhub.persistence.entities.Parking;

@Mapper(componentModel = "spring")
public interface AddressMapper {
    //AddressMapper  INSTANCE = Mappers.getMapper( AddressMapper.class );
    AddressModel addressToAddressModel(Address address);
    Address addressModelToAddress(AddressModel addressModel);
    AddressDTO addressModelToAddressDTO(AddressModel addressModel);
    AddressModel addressDTOToAddressModel(AddressDTO addressDTO);
}
