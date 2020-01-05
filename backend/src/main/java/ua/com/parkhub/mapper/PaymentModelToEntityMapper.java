package ua.com.parkhub.mapper;

import org.springframework.stereotype.Component;
import ua.com.parkhub.model.PaymentModel;
import ua.com.parkhub.persistence.entities.Payment;

@Component
public class PaymentModelToEntityMapper implements Mapper<PaymentModel, Payment> {
    @Override
    public Payment transform(PaymentModel from) {
        return null;
    }
}
