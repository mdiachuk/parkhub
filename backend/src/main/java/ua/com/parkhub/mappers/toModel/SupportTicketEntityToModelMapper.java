package ua.com.parkhub.mappers.toModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.com.parkhub.model.Mapper;
import ua.com.parkhub.model.SupportTicketModel;
import ua.com.parkhub.persistence.entities.SupportTicket;

@Component
public class SupportTicketEntityToModelMapper implements Mapper<SupportTicket, SupportTicketModel> {

    SupportTicketTypeEntityToModelMapper supportTicketTypeEntityToModelMapper;
    UserEntityToModelMapper userEntityToModelMapper;

    @Autowired
    public SupportTicketEntityToModelMapper(SupportTicketTypeEntityToModelMapper supportTicketTypeEntityToModelMapper) {
        this.supportTicketTypeEntityToModelMapper = supportTicketTypeEntityToModelMapper;
    }

    @Override
    public SupportTicketModel transform(SupportTicket from) {
        SupportTicketModel supportTicketModel = new SupportTicketModel();
        supportTicketModel.setDescription(from.getDescription());
        supportTicketModel.setId(from.getId());
        supportTicketModel.setSolved(from.isSolved());
        supportTicketModel.setSupportTicketType(supportTicketTypeEntityToModelMapper.transform(from.getSupportTicketType()));
        return supportTicketModel;
    }
}
