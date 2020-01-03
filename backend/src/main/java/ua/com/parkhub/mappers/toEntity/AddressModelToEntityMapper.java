package ua.com.parkhub.mappers.toEntity;

import org.springframework.stereotype.Component;
import ua.com.parkhub.model.AddressModel;
import ua.com.parkhub.model.Mapper;
import ua.com.parkhub.persistence.entities.Address;

@Component
public class AddressModelToEntityMapper implements Mapper<AddressModel, Address> {
    @Override
    public Address transform(AddressModel from) {
        return null;
    }
}
