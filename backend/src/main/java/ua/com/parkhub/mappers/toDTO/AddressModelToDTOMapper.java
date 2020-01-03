package ua.com.parkhub.mappers.toDTO;

import org.springframework.stereotype.Component;
import ua.com.parkhub.dto.AddressDTO;
import ua.com.parkhub.model.AddressModel;
import ua.com.parkhub.model.Mapper;

@Component
public class AddressModelToDTOMapper implements Mapper<AddressModel, AddressDTO> {

    @Override
    public AddressDTO transform(AddressModel from) {
        AddressDTO addressDTO = new AddressDTO();
        addressDTO.setAddress(from.toString());
        return addressDTO;
    }
}
