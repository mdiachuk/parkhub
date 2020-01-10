package ua.com.parkhub.service;

import ua.com.parkhub.model.BookingModel;
import ua.com.parkhub.model.CustomerModel;

import java.util.Optional;

public interface IBookingService {

    int findPrice(String phoneNumber);
    Optional<BookingModel> findPrepaidBooking(CustomerModel customerModel);
    BookingModel addBooking(String phoneNumber, String carNumber, long slotId);
}
