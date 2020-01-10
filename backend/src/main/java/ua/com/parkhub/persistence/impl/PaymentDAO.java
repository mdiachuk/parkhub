package ua.com.parkhub.persistence.impl;

import org.springframework.stereotype.Repository;
import ua.com.parkhub.mappers.Mapper;
import ua.com.parkhub.model.BookingModel;
import ua.com.parkhub.model.ParkingModel;
import ua.com.parkhub.model.PaymentModel;
import ua.com.parkhub.persistence.entities.Payment;

import java.util.Optional;

@Repository
public class PaymentDAO extends ElementDAO<Payment, PaymentModel> {

    public PaymentDAO(Mapper<Payment, PaymentModel> entityToModel, Mapper<PaymentModel, Payment> modelToEntity) {
        super(Payment.class, modelToEntity, entityToModel);
    }

    public Optional<PaymentModel> findPaymentByBooking (BookingModel booking){
        return this.findOneByFieldEqual("booking", booking.getId());
    }
}

