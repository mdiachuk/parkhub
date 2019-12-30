package ua.com.parkhub.mappers.fromModelToEntity;

import org.springframework.stereotype.Component;
import ua.com.parkhub.mappers.Mapper;
import ua.com.parkhub.model.AddressModel;
import ua.com.parkhub.persistence.entities.Address;

@Component
public class AddressModelToEntityMapper implements Mapper<AddressModel, Address> {
    @Override
    public Address transform(AddressModel from) {
        return null;
    }
}
