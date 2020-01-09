package ua.com.parkhub.service;

import ua.com.parkhub.model.BookingModel;

public interface IBookingService {

    int findPrice(String phoneNumber);
    BookingModel addBooking(String phoneNumber, String carNumber, long slotId);

}
