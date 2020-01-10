package ua.com.parkhub.service.impl;

import org.springframework.stereotype.Service;
import ua.com.parkhub.model.BookingModel;
import ua.com.parkhub.model.PaymentModel;
import ua.com.parkhub.persistence.impl.PaymentDAO;
import ua.com.parkhub.service.IPaymentService;

@Service
public class PaymentService implements IPaymentService {

    private final PaymentDAO paymentDAO;

    public PaymentService(PaymentDAO paymentDAO) {
        this.paymentDAO = paymentDAO;
    }

    @Override
    public PaymentModel addPayment(BookingModel bookingModel, int price) {
        PaymentModel paymentModel = new PaymentModel();
        paymentModel.setBooking(bookingModel);
        paymentModel.setPrice(price);
        paymentModel.setPaid(true);
        paymentDAO.addElement(paymentModel);
        return paymentModel;
    }
}
