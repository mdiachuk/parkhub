package ua.com.parkhub.mapper;

import org.springframework.stereotype.Component;
import ua.com.parkhub.model.SupportTicketModel;
import ua.com.parkhub.persistence.entities.SupportTicket;

@Component
public class SupportTicketModelToEntityMapper implements Mapper<SupportTicketModel, SupportTicket> {
    @Override
    public SupportTicket transform(SupportTicketModel from) {
        return null;
    }
}
