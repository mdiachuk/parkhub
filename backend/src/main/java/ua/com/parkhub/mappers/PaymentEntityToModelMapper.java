package ua.com.parkhub.mappers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.com.parkhub.model.PaymentModel;
import ua.com.parkhub.persistence.entities.Payment;

@Component
public class PaymentEntityToModelMapper implements Mapper<Payment, PaymentModel> {

    BookingEntityToModelMapper bookingEntityToModelMapper;

    @Autowired
    public PaymentEntityToModelMapper(BookingEntityToModelMapper bookingEntityToModelMapper) {
        this.bookingEntityToModelMapper = bookingEntityToModelMapper;
    }

    @Override
    public PaymentModel transform(Payment from) {
        PaymentModel paymentModel = new PaymentModel();
        paymentModel.setId(from.getId());
        paymentModel.setPaid(from.isPaid());
        paymentModel.setPrice(from.getPrice());
        paymentModel.setBooking(bookingEntityToModelMapper.transform(from.getBooking()));
        return paymentModel;
    }
}
