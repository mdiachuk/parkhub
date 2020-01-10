package ua.com.parkhub.service.impl;

import org.springframework.stereotype.Service;
import ua.com.parkhub.model.BookingModel;
import ua.com.parkhub.model.PaymentModel;
import ua.com.parkhub.persistence.impl.PaymentDAO;
import ua.com.parkhub.service.IPaymentService;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Service
public class PaymentService implements IPaymentService {

    private final PaymentDAO paymentDAO;

    public PaymentService(PaymentDAO paymentDAO) {
        this.paymentDAO = paymentDAO;
    }

    public int calculatePrice(BookingModel bookingModel, int tariff) {
        LocalDateTime checkIn = bookingModel.getCheckIn();
        LocalDateTime checkOut = bookingModel.getCheckOut();
        long hours = ChronoUnit.HOURS.between(checkIn, checkOut);
        return hours > 0 ? (int) (tariff * hours) : tariff;
    }

    @Override
    public PaymentModel addPayment(BookingModel bookingModel, int tariff) {
        PaymentModel paymentModel = new PaymentModel();
        paymentModel.setBooking(bookingModel);
        int price = calculatePrice(bookingModel, tariff);
        paymentModel.setPrice(price);
        paymentModel.setPaid(true);
        paymentDAO.addElement(paymentModel);
        return paymentModel;
    }
}
