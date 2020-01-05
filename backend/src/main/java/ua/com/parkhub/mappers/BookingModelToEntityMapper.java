package ua.com.parkhub.mappers;

import org.springframework.stereotype.Component;
import ua.com.parkhub.model.BookingModel;
import ua.com.parkhub.persistence.entities.Booking;

@Component
public class BookingModelToEntityMapper implements Mapper<BookingModel, Booking> {
    @Override
    public Booking transform(BookingModel from) {
        return null;
    }
}

