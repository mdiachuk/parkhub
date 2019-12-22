package ua.com.parkhub.persistence.impl;

import org.springframework.stereotype.Repository;
import ua.com.parkhub.persistence.entities.AddressEntity;

@Repository
public class AddressDAO  extends ElementDAO<AddressEntity>  {

    public AddressDAO() {
        super(AddressEntity.class);
    }
}
