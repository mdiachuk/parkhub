package ua.com.parkhub.backend.persistense.impl;

import ua.com.parkhub.backend.persistense.entities.Booking;

public class BookingDAO extends ElementDAO<Booking> {
    public BookingDAO() {
        super(Booking.class);
    }
}
