package ua.com.parkhub.backend.persistense.impl;

import ua.com.parkhub.backend.persistense.entities.Address;

public class AddressDAO extends ElementDAO<Address> {
    public AddressDAO() {
        super(Address.class);
    }
}
