package ua.com.parkhub.service.impl;

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
    }
}
