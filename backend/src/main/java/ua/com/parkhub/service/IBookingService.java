package ua.com.parkhub.service;

import ua.com.parkhub.model.BookingModel;
import ua.com.parkhub.model.PaymentModel;
import ua.com.parkhub.model.CustomerModel;
import ua.com.parkhub.persistence.entities.Slot;

import java.util.List;
import java.util.Optional;

public interface IBookingService {

    PaymentModel addBooking(String phoneNumber, String carNumber, long slotId, long checkIn, long checkOut, int price);

    Optional<BookingModel> findBookingByIdAndDateTimeRange(long id, long checkIn, long checkOut);

    int findPrice(String phoneNumber);

    Optional<BookingModel> findPrepaidBooking(CustomerModel customerModel);

    List<Slot> findAllAvailableSlots(long checkIn, long checkOut);
}
