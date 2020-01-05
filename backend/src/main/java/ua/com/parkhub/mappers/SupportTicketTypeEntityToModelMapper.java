package ua.com.parkhub.mappers;

import org.springframework.stereotype.Component;
import ua.com.parkhub.model.SupportTicketTypeModel;
import ua.com.parkhub.persistence.entities.SupportTicketType;

@Component
public class SupportTicketTypeEntityToModelMapper implements Mapper<SupportTicketType, SupportTicketTypeModel> {
    @Override
    public ua.com.parkhub.model.SupportTicketTypeModel transform(SupportTicketType from) {
        ua.com.parkhub.model.SupportTicketTypeModel supportTicketTypeModel = new ua.com.parkhub.model.SupportTicketTypeModel();
        supportTicketTypeModel.setId(from.getId());
        supportTicketTypeModel.setActive(from.isActive());
        supportTicketTypeModel.setType(from.getType());
        return supportTicketTypeModel;
    }
}
