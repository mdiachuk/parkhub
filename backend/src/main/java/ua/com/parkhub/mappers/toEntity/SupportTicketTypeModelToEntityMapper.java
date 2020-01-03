package ua.com.parkhub.mappers.toEntity;

import org.springframework.stereotype.Component;
import ua.com.parkhub.model.Mapper;
import ua.com.parkhub.persistence.entities.SupportTicketTypeModel;

@Component
public class SupportTicketTypeModelToEntityMapper implements Mapper<ua.com.parkhub.model.SupportTicketTypeModel, SupportTicketTypeModel> {
    @Override
    public SupportTicketTypeModel transform(ua.com.parkhub.model.SupportTicketTypeModel from) {
        return null;
    }
}
