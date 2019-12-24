package ua.com.parkhub.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ua.com.parkhub.dto.AddressDTO;
import ua.com.parkhub.model.AddressModel;
import ua.com.parkhub.persistence.entities.Address;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AddressMapperTest {

    AddressMapper addressMapper;

    @BeforeEach
    void init(){
        addressMapper = new AddressMapper();
    }

    @Test
    void fromEntityToModel() {
        Address address = new Address();
        address.setId((1L));
        address.setCity("Kyiv");
        address.setStreet("Zarichna");
        address.setBuilding("2");

        AddressModel addressModel = addressMapper.fromEntityToModel(address);
        assertEquals(address.getCity(), addressModel.getCity());
        assertEquals(address.getStreet(), addressModel.getStreet());
        assertEquals(address.getBuilding(), addressModel.getBuilding());
    }

    @Test
    void fromModelToDTO() {
        AddressModel address = new AddressModel();
        address.setCity("Kyiv");
        address.setStreet("Zarichna");
        address.setBuilding("2");

        AddressDTO addressDTO = addressMapper.fromModelToDTO(address);

        assertEquals(address.toString(), addressDTO.getAddress());

    }
}