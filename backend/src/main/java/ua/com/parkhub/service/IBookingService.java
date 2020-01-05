package ua.com.parkhub.service;

import ua.com.parkhub.model.BookingModel;

public interface IBookingService {

    BookingModel addBooking(String phoneNumber, String carNumber, long slotId);

}
