package ua.com.parkhub.mappers.ModelToEntity;

import org.springframework.stereotype.Component;
import ua.com.parkhub.mappers.Mapper;
import ua.com.parkhub.model.SupportTicketTypeModel;

@Component
public class SupportTicketTypeModelToEntityMapper implements Mapper<SupportTicketTypeModel, SupportTicketTypeModel> {
    @Override
    public SupportTicketTypeModel transform(ua.com.parkhub.model.SupportTicketTypeModel from) {
        return null;
    }
}
