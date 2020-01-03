package ua.com.parkhub.mappers.toModel;

import org.springframework.stereotype.Component;
import ua.com.parkhub.model.AddressModel;
import ua.com.parkhub.model.Mapper;
import ua.com.parkhub.persistence.entities.Address;

@Component
public class AddressEntityToModelMapper implements Mapper<Address, AddressModel> {
    @Override
    public AddressModel transform(Address from) {
        AddressModel addressModel = new AddressModel();
        addressModel.setId(from.getId());
        addressModel.setCity(from.getCity());
        addressModel.setStreet(from.getStreet());
        addressModel.setBuilding(from.getBuilding());
        return addressModel;
    }
}
