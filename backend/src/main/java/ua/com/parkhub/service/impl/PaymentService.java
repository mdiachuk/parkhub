package ua.com.parkhub.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.com.parkhub.exceptions.PaymentException;
import ua.com.parkhub.model.BookingModel;
import ua.com.parkhub.model.PaymentModel;
import ua.com.parkhub.persistence.entities.Booking;
import ua.com.parkhub.persistence.entities.Customer;
import ua.com.parkhub.persistence.entities.Payment;
import ua.com.parkhub.persistence.impl.BookingDAO;
import ua.com.parkhub.persistence.impl.CustomerDAO;
import ua.com.parkhub.persistence.impl.PaymentDAO;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

@Service
@Transactional
public class PaymentService {

    private PaymentDAO paymentDAO;

    @Autowired
    public PaymentService(PaymentDAO paymentDAO) {
        this.paymentDAO = paymentDAO;
    }

    public PaymentDAO getPaymentDAO() {
        return paymentDAO;
    }

    public PaymentModel findPaymentByBooking(BookingModel bookingModel){
        Optional<PaymentModel> paymentModel = paymentDAO.findPaymentByBooking(bookingModel);
        if (paymentModel.isPresent()){
            return paymentModel.get();
        }else {
            throw new PaymentException("Failed to find payment by booking");
        }
    }
}
