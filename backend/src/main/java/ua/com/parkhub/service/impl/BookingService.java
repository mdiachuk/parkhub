package ua.com.parkhub.service.impl;


import org.springframework.stereotype.Service;
import ua.com.parkhub.model.BookingModel;
import ua.com.parkhub.service.IBookingService;

@Service
public class BookingService implements IBookingService {

    @Override
    public BookingModel addBooking(String phoneNumber, String carNumber, long slotId) {
        return null;
    }
}
