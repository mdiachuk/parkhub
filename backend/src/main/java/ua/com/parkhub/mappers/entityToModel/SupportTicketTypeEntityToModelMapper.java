package ua.com.parkhub.mappers.entityToModel;

import org.springframework.stereotype.Component;
import ua.com.parkhub.mappers.Mapper;
import ua.com.parkhub.model.SupportTicketType;
import ua.com.parkhub.model.SupportTicketTypeModel;

@Component
public class SupportTicketTypeEntityToModelMapper implements Mapper<SupportTicketType, SupportTicketTypeModel> {
    @Override
    public SupportTicketTypeModel transform(SupportTicketType from) {
        SupportTicketTypeModel supportTicketTypeModel = new SupportTicketTypeModel();
        supportTicketTypeModel.setId(from.getId());
        supportTicketTypeModel.setActive(from.isActive());
        supportTicketTypeModel.setType(from.getType());
        return supportTicketTypeModel;
    }
}
