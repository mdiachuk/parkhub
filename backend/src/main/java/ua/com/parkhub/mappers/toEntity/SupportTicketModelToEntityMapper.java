package ua.com.parkhub.mappers.toEntity;

import org.springframework.stereotype.Component;
import ua.com.parkhub.model.Mapper;
import ua.com.parkhub.model.SupportTicket;
import ua.com.parkhub.model.SupportTicketModel;

@Component
public class SupportTicketModelToEntityMapper implements Mapper<SupportTicketModel, SupportTicket> {
    @Override
    public SupportTicket transform(SupportTicketModel from) {
        return null;
    }
}
