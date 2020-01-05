//package ua.com.parkhub.mapper;
//
//import org.modelmapper.ModelMapper;
//import org.springframework.stereotype.Component;
//import ua.com.parkhub.dto.AddressDTO;
//import ua.com.parkhub.model.AddressModel;
//import ua.com.parkhub.persistence.entities.Address;
//
//import java.util.Objects;
//
//@Component
//public class AddressMapper {
//
//    ModelMapper modelMapper = new ModelMapper();
//
//    public AddressModel fromEntityToModel(Address address) {
//        return Objects.isNull(address) ? null : modelMapper.map(address, AddressModel.class);
//    }
//
//    public AddressDTO fromModelToDTO(AddressModel addressModel){
//        return new AddressDTO(addressModel.toString());
//    }
//}
