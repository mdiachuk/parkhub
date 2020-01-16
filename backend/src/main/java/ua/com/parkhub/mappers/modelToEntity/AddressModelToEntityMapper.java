package ua.com.parkhub.mappers.modelToEntity;

import org.springframework.stereotype.Component;
import ua.com.parkhub.mappers.Mapper;
import ua.com.parkhub.model.AddressModel;
import ua.com.parkhub.persistence.entities.Address;

@Component
public class AddressModelToEntityMapper implements Mapper<AddressModel, Address> {

    @Override
    public Address transform(AddressModel from) {
        if(from == null) {
            return null;
        }
        Address address = new Address();
        address.setId(from.getId());
        address.setStreet(from.getStreet());
        address.setCity(from.getCity());
        address.setBuilding(from.getBuilding());
        address.setLat(from.getLat());
        address.setLon(from.getLon());
        return address;
    }
}
