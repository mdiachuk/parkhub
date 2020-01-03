package ua.com.parkhub.mappers.toEntity;

import org.springframework.stereotype.Component;
import ua.com.parkhub.model.Mapper;
import ua.com.parkhub.model.SupportTicketTypeModel;
import ua.com.parkhub.persistence.entities.SupportTicketType;

@Component
public class SupportTicketTypeModelToEntityMapper implements Mapper<SupportTicketTypeModel, ua.com.parkhub.persistence.entities.SupportTicketType> {
    @Override
    public SupportTicketType transform(SupportTicketTypeModel from) {
        return null;
    }
}
