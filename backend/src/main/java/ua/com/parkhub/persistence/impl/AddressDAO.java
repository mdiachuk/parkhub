package ua.com.parkhub.persistence.impl;

import org.springframework.stereotype.Repository;
import ua.com.parkhub.persistence.entities.Address;

@Repository
public class AddressDAO  extends ElementDAO<Address> {
    public AddressDAO() {
        super(Address.class);
    }
}
