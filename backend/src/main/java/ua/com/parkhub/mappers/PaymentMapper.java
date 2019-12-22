package ua.com.parkhub.mappers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.com.parkhub.model.PaymentModel;
import ua.com.parkhub.persistence.entities.Payment;

import java.util.Optional;

@Component
public class PaymentMapper implements Mapper<Payment, PaymentModel> {

    private BookingMapper bookingMapper;

    @Autowired
    public PaymentMapper(BookingMapper bookingMapper) {
        this.bookingMapper = bookingMapper;
    }

    @Override
    public Optional<Payment> toEntity(PaymentModel model) {
        Payment payment = new Payment();
        payment.setBooking(bookingMapper.toEntity(model.getBooking()).get());
        payment.setPrice(model.getPrice());
        payment.setPaid(model.isPaid());
        return Optional.of(payment);
    }

    @Override
    public Optional<PaymentModel> toModel(Payment entity) {
        PaymentModel paymentModel = new PaymentModel();
        paymentModel.setBooking(bookingMapper.toModel(entity.getBooking()).get());
        paymentModel.setPrice(entity.getPrice());
        paymentModel.setPaid(entity.isPaid());
        return Optional.of(paymentModel);
    }
}
