package ua.com.parkhub.mappers.modelToEntity;

import org.springframework.stereotype.Component;
import ua.com.parkhub.mappers.Mapper;
import ua.com.parkhub.model.enums.TicketTypeModel;
import ua.com.parkhub.persistence.entities.SupportTicketType;

@Component
public class SupportTicketTypeModelToEntityMapper implements Mapper<TicketTypeModel, SupportTicketType> {

    @Override
    public SupportTicketType transform(TicketTypeModel from) {
        if (from == null) {
            return null;
        }
        SupportTicketType supportTicketType = new SupportTicketType();
        supportTicketType.setId(from.getId());
        supportTicketType.setType(from.getValue());
        return supportTicketType;
    }
}


