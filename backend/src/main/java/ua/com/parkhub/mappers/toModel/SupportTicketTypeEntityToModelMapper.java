package ua.com.parkhub.mappers.toModel;

import org.springframework.stereotype.Component;
import ua.com.parkhub.mapper.Mapper;
import ua.com.parkhub.persistence.entities.SupportTicketTypeModel;

@Component
public class SupportTicketTypeEntityToModelMapper implements Mapper<SupportTicketTypeModel, ua.com.parkhub.model.SupportTicketTypeModel> {
    @Override
    public ua.com.parkhub.model.SupportTicketTypeModel transform(SupportTicketTypeModel from) {
        ua.com.parkhub.model.SupportTicketTypeModel supportTicketTypeModel = new ua.com.parkhub.model.SupportTicketTypeModel();
        supportTicketTypeModel.setId(from.getId());
        supportTicketTypeModel.setActive(from.isActive());
        supportTicketTypeModel.setType(from.getType());
        return supportTicketTypeModel;
    }
}
