package ua.com.parkhub.mappers.modelToEntity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.com.parkhub.mappers.Mapper;
import ua.com.parkhub.model.PaymentModel;
import ua.com.parkhub.persistence.entities.Payment;

@Component
public class PaymentModelToEntityMapper implements Mapper<PaymentModel, Payment> {

    BookingModelToEntityMapper bookingModelToEntityMapper;

    @Autowired
    public PaymentModelToEntityMapper(BookingModelToEntityMapper bookingModelToEntityMapper) {
        this.bookingModelToEntityMapper = bookingModelToEntityMapper;
    }

    @Override
    public Payment transform(PaymentModel from) {
        if (from == null) {
            return null;
        }
        Payment payment = new Payment();
        payment.setId(from.getId());
        payment.setPaid(from.isPaid());
        payment.setPrice(from.getPrice());
        payment.setCancelled(from.isCancelled());
        payment.setBooking(bookingModelToEntityMapper.transform(from.getBooking()));
        return payment;
    }
}
