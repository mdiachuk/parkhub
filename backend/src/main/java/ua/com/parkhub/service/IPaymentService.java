package ua.com.parkhub.service;

import ua.com.parkhub.model.BookingModel;
import ua.com.parkhub.model.PaymentModel;

public interface IPaymentService {
    PaymentModel findPaymentByBooking(BookingModel bookingModel);
    void updateIsCancelled(PaymentModel paymentModel, boolean isCancelled);
}
