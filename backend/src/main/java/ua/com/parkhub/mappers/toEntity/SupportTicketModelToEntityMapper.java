package ua.com.parkhub.mappers.toEntity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.com.parkhub.mappers.Mapper;
import ua.com.parkhub.model.SupportTicketModel;
import ua.com.parkhub.persistence.entities.SupportTicket;

@Component
public class SupportTicketModelToEntityMapper implements Mapper<SupportTicketModel, SupportTicket> {

    SupportTicketTypeModelToEntityMapper supportTicketTypeModelToEntityMapper;

    @Autowired
    public SupportTicketModelToEntityMapper(SupportTicketTypeModelToEntityMapper supportTicketTypeModelToEntityMapper) {
        this.supportTicketTypeModelToEntityMapper = supportTicketTypeModelToEntityMapper;
    }

    @Override
    public SupportTicket transform(SupportTicketModel from) {
        SupportTicket supportTicket = new SupportTicket();
        supportTicket.setId(from.getId());
        supportTicket.setDescription(from.getDescription());
        supportTicket.setSolved(from.isSolved());
        supportTicket.setSupportTicketType(supportTicketTypeModelToEntityMapper.transform(from.getSupportTicketType()));
        return supportTicket;
    }
}
