package ua.com.parkhub.service;

import ua.com.parkhub.model.BookingModel;
import ua.com.parkhub.model.PaymentModel;
import ua.com.parkhub.persistence.entities.Payment;

public interface IPaymentService {
    void addPayment(BookingModel bookingModel);
}
