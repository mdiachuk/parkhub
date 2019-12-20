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
        return Optional.of(new Payment(model.getPrice(),
                model.isPaid(),
                bookingMapper.toEntity(model.getBooking()).get()));
    }

    @Override
    public Optional<PaymentModel> toModel(Payment entity) {
        return Optional.of(new PaymentModel(bookingMapper.toModel(entity.getBooking()).get(),
                entity.getPrice(),
                entity.isPaid()));
    }
}
