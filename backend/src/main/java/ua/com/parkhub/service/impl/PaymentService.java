package ua.com.parkhub.service.impl;

import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.com.parkhub.exceptions.BookingException;
import ua.com.parkhub.exceptions.StatusCode;
import ua.com.parkhub.model.BookingModel;
import ua.com.parkhub.model.PaymentModel;
import ua.com.parkhub.persistence.impl.PaymentDAO;
import ua.com.parkhub.service.IPaymentService;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Service
public class PaymentService implements IPaymentService {

    private final PaymentDAO paymentDAO;

@Service
@Transactional
public class PaymentService implements IPaymentService {

    private static final Logger logger = LoggerFactory.getLogger(PaymentService.class);

    private PaymentDAO paymentDAO;

    @Autowired
    public PaymentService(PaymentDAO paymentDAO) {
        this.paymentDAO = paymentDAO;
    }

    public void updateIsCancelled(PaymentModel paymentModel, boolean isCancelled){
        paymentModel.setCancelled(isCancelled);
        paymentDAO.updateElement(paymentModel);
    }

    public PaymentModel findPaymentByBooking(BookingModel bookingModel){
       return paymentDAO.findPaymentByBooking(bookingModel).orElseThrow(() -> new BookingException(StatusCode.BOOKING_NOT_FOUND));
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
