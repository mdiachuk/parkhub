package ua.com.parkhub.mappers;

import org.springframework.stereotype.Component;
import ua.com.parkhub.model.AddressModel;
import ua.com.parkhub.persistence.entities.Address;

import java.util.Optional;

@Component
public class AddressMapper implements Mapper<Address, AddressModel> {

    @Override
    public Optional<Address> toEntity(AddressModel model) {
        Address address = new Address();
        address.setCity(model.getCity());
        address.setStreet(model.getStreet());
        address.setBuilding(model.getBuilding());
        return Optional.of(address);
    }

    @Override
    public Optional<AddressModel> toModel(Address entity) {
        AddressModel addressModel = new AddressModel();
        addressModel.setCity(entity.getCity());
        addressModel.setStreet(entity.getStreet());
        addressModel.setBuilding(entity.getBuilding());
        return Optional.of(addressModel);
    }
}
