package ua.com.parkhub.persistence.impl;

import org.springframework.stereotype.Repository;
import ua.com.parkhub.persistence.entities.Booking;

@Repository
public class BookingDAO extends ElementDAO<Booking> {

    public BookingDAO() {
        super(Booking.class);
    }
}

