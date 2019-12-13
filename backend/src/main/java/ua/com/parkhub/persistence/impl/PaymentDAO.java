package ua.com.parkhub.persistence.impl;

import org.springframework.stereotype.Repository;
import ua.com.parkhub.persistence.entities.Payment;

@Repository
public class PaymentDAO extends ElementDAO<Payment> {

    public PaymentDAO() {
        super(Payment.class);
    }
}

