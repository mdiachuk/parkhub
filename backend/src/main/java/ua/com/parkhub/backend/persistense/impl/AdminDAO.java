package ua.com.parkhub.backend.persistense.impl;

import ua.com.parkhub.backend.persistense.entities.Admin;

public class AdminDAO extends ElementDAO<Admin> {
    public AdminDAO() {
        super(Admin.class);
    }
}
