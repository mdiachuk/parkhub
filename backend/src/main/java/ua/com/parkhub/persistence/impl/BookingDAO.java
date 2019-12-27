package ua.com.parkhub.persistence.impl;

import org.springframework.stereotype.Repository;
import ua.com.parkhub.mappers.Mapper;
import ua.com.parkhub.model.BookingModel;
import ua.com.parkhub.model.ParkingModel;
import ua.com.parkhub.persistence.entities.Booking;
import ua.com.parkhub.persistence.entities.Parking;

@Repository
public class BookingDAO extends ElementDAO<Booking, BookingModel> {

    public BookingDAO(Mapper<Booking, BookingModel> entityToModel, Mapper<BookingModel, Booking> modelToEntity) {
        super(Booking.class, modelToEntity, entityToModel);
    }
}

