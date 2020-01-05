package ua.com.parkhub.mappers;

import org.springframework.stereotype.Component;
import ua.com.parkhub.model.BookingModel;
import ua.com.parkhub.persistence.entities.Booking;

@Component
public class BookingEntityToModelMapper implements Mapper<Booking, BookingModel> {

    @Override
    public BookingModel transform(Booking from) {
        return null;
    }
}
