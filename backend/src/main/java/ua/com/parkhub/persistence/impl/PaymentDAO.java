package ua.com.parkhub.persistence.impl;

import org.springframework.stereotype.Repository;
import ua.com.parkhub.mappers.Mapper;
import ua.com.parkhub.model.BookingModel;
import ua.com.parkhub.model.PaymentModel;
import ua.com.parkhub.persistence.entities.Booking;
import ua.com.parkhub.persistence.entities.Payment;

import java.util.Optional;

@Repository
public class PaymentDAO extends ElementDAO<Payment, PaymentModel> {

    public PaymentDAO(Mapper<Payment, PaymentModel> entityToModel, Mapper<PaymentModel, Payment> modelToEntity) {
        super(Payment.class, modelToEntity, entityToModel);
    }

    public PaymentModel addWithResponse(PaymentModel paymentModel) {
        Payment entity = modelToEntity.transform(paymentModel);
        emp.persist(entity);
        return entityToModel.transform(entity);
    }

    public Optional<PaymentModel> findPaymentByBooking(BookingModel booking) {
        return this.findOneByFieldEqual("booking", booking.getId());
    }
}

