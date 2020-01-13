package ua.com.parkhub.service;

import ua.com.parkhub.model.BookingModel;
import ua.com.parkhub.model.PaymentModel;
import ua.com.parkhub.persistence.entities.Payment;

public interface IPaymentService {
    int calculatePrice(BookingModel bookingModel, int price);
    PaymentModel addPayment(BookingModel bookingModel, int price);
    PaymentModel findPaymentByBooking(BookingModel bookingModel);
    int findPriceIfCancelled(BookingModel bookingModel);
}
