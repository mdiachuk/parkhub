package ua.com.parkhub.service;

import ua.com.parkhub.model.BookingModel;

import java.util.Optional;

public interface IBookingService {

    BookingModel addBooking(String phoneNumber, String carNumber, long slotId, long checkIn, long checkOut);

    Optional<BookingModel> findBookingByIdAndDateTimeRange(long id, long checkIn, long checkOut);

}
