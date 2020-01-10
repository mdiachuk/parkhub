package ua.com.parkhub.persistence.impl;

import org.springframework.stereotype.Repository;

import ua.com.parkhub.mappers.Mapper;
import ua.com.parkhub.model.PaymentModel;
import ua.com.parkhub.persistence.entities.Payment;

@Repository
public class PaymentDAO extends ElementDAO<Payment, PaymentModel> {

    public PaymentDAO(Mapper<Payment, PaymentModel> entityToModel, Mapper<PaymentModel, Payment> modelToEntity) {
        super(Payment.class, modelToEntity, entityToModel);
    }
}

