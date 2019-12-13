package ua.com.parkhub.backend.persistense.impl;

import ua.com.parkhub.backend.persistense.entities.Payment;

public class PaymentDAO extends ElementDAO<Payment> {
    public PaymentDAO() {
        super(Payment.class);
    }
}
