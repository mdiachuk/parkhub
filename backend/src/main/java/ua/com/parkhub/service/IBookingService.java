package ua.com.parkhub.service;

import ua.com.parkhub.model.Booking;

public interface IBookingService {

    public int findPrice(String phoneNumber);
    Booking addBooking(String phoneNumber, String carNumber, long slotId);

}
