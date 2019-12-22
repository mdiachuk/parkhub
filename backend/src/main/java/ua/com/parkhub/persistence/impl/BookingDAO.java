package ua.com.parkhub.persistence.impl;

import org.springframework.stereotype.Repository;
import ua.com.parkhub.persistence.entities.BookingEntity;

@Repository
public class BookingDAO extends ElementDAO<BookingEntity> {

    public BookingDAO() {
        super(BookingEntity.class);
    }
}

