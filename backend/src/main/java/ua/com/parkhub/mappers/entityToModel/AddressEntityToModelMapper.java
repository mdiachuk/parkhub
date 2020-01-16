package ua.com.parkhub.mappers.entityToModel;

import org.springframework.stereotype.Component;
import ua.com.parkhub.mappers.Mapper;
import ua.com.parkhub.model.AddressModel;
import ua.com.parkhub.persistence.entities.Address;

@Component
public class AddressEntityToModelMapper implements Mapper<Address, AddressModel> {

    @Override
    public AddressModel transform(Address from) {
        if(from == null) {
            return null;
        }
        AddressModel addressModel = new AddressModel();
        addressModel.setId(from.getId());
        addressModel.setCity(from.getCity());
        addressModel.setStreet(from.getStreet());
        addressModel.setBuilding(from.getBuilding());
        addressModel.setLon(from.getLon());
        addressModel.setLat(from.getLat());
        return addressModel;
    }
}