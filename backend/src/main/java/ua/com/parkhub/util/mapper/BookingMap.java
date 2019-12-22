package ua.com.parkhub.util.mapper;

import org.modelmapper.PropertyMap;
import ua.com.parkhub.dto.BookingDTO;
import ua.com.parkhub.model.Booking;

public class BookingMap extends PropertyMap<Booking, BookingDTO> {

    @Override
    protected void configure() {

    }
}
