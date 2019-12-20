package ua.com.parkhub.mappers;

import org.springframework.stereotype.Component;
import ua.com.parkhub.model.AddressModel;
import ua.com.parkhub.persistence.entities.Address;

import java.util.Optional;

@Component
public class AddressMapper implements Mapper<Address, AddressModel> {

    @Override
    public Optional<Address> toEntity(AddressModel model) {
        return Optional.of(new Address(model.getCity(),
                model.getStreet(),
                model.getBuilding()));
    }

    @Override
    public Optional<AddressModel> toModel(Address entity) {
        return Optional.of(new AddressModel(entity.getCity(),
                entity.getStreet(),
                entity.getBuilding()));
    }
}
