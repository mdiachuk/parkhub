package ua.com.parkhub.persistence.impl;

import org.springframework.stereotype.Repository;
import ua.com.parkhub.persistence.entities.PaymentEntity;

@Repository
public class PaymentDAO extends ElementDAO<PaymentEntity> {

    public PaymentDAO() {
        super(PaymentEntity.class);
    }
}

