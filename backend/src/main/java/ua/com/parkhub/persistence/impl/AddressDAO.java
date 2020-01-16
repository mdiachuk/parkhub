package ua.com.parkhub.persistence.impl;

import org.springframework.stereotype.Repository;
import ua.com.parkhub.mappers.Mapper;
import ua.com.parkhub.model.AddressModel;
import ua.com.parkhub.persistence.entities.Address;

@Repository
public class AddressDAO  extends ElementDAO<Address, AddressModel> {

    public AddressDAO(Mapper<Address, AddressModel> entityToModel, Mapper<AddressModel, Address> modelToEntity) {
        super(Address.class, modelToEntity, entityToModel);
    }

    public AddressModel addWithResponse(AddressModel addressModel) {
        Address entity = modelToEntity.transform(addressModel);
        emp.persist(entity);
        return entityToModel.transform(entity);
    }
}

