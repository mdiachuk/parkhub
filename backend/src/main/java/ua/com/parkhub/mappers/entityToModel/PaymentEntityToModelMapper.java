package ua.com.parkhub.mappers.entityToModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.com.parkhub.mappers.Mapper;
import ua.com.parkhub.model.BookingModel;
import ua.com.parkhub.model.PaymentModel;
import ua.com.parkhub.persistence.entities.Booking;
import ua.com.parkhub.persistence.entities.Payment;

@Component
public class PaymentEntityToModelMapper implements Mapper<Payment, PaymentModel> {

    private Mapper<Booking, BookingModel> bookingEntityToModelMapper;

    @Autowired
    public PaymentEntityToModelMapper(Mapper<Booking, BookingModel> bookingEntityToModelMapper) {
        this.bookingEntityToModelMapper = bookingEntityToModelMapper;
    }

    @Override
    public PaymentModel transform(Payment from) {
        if(from == null) {
            return null;
        }
        PaymentModel paymentModel = new PaymentModel();
        paymentModel.setId(from.getId());
        paymentModel.setPaid(from.isPaid());
        paymentModel.setCancelled(from.isCancelled());
        paymentModel.setPrice(from.getPrice());
        paymentModel.setBooking(bookingEntityToModelMapper.transform(from.getBooking()));
        return paymentModel;
    }
}
