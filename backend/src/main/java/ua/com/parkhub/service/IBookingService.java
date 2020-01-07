package ua.com.parkhub.service;

public interface IBookingService {

    public int findPrice(String phoneNumber);
    Booking addBooking(String phoneNumber, String carNumber, long slotId);

}
