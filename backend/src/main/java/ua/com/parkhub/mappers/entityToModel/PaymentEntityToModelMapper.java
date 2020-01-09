package ua.com.parkhub.mappers.entityToModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.com.parkhub.exceptions.BookingException;
import ua.com.parkhub.exceptions.StatusCode;
import ua.com.parkhub.mappers.Mapper;
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
        if (from == null){
            throw new BookingException(StatusCode.BOOKING_NOT_FOUND);
        }
        PaymentModel paymentModel = new PaymentModel();
        paymentModel.setId(from.getId());
        paymentModel.setPaid(from.isPaid());
        paymentModel.setPrice(from.getPrice());
        paymentModel.setBooking(bookingEntityToModelMapper.transform(from.getBooking()));
        return paymentModel;
    }
}
