package ua.com.parkhub.mappers.toModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.com.parkhub.mappers.Mapper;
import ua.com.parkhub.model.SupportTicketModel;
import ua.com.parkhub.persistence.entities.SupportTicket;

import java.util.stream.Collectors;

@Component
public class SupportTicketEntityToModelMapper implements Mapper<SupportTicket, SupportTicketModel> {

    SupportTicketTypeEntityToModelMapper supportTicketTypeEntityToModelMapper;
    UserEntityToModelMapper userEntityToModelMapper;

    @Autowired
    public SupportTicketEntityToModelMapper(SupportTicketTypeEntityToModelMapper supportTicketTypeEntityToModelMapper, UserEntityToModelMapper userEntityToModelMapper) {
        this.supportTicketTypeEntityToModelMapper = supportTicketTypeEntityToModelMapper;
        this.userEntityToModelMapper = userEntityToModelMapper;
    }

    @Override
    public SupportTicketModel transform(SupportTicket from) {
        SupportTicketModel supportTicketModel = new SupportTicketModel();
        supportTicketModel.setDescription(from.getDescription());
        supportTicketModel.setId(from.getId());
        supportTicketModel.setSolved(from.isSolved());
        supportTicketModel.setSupportTicketType(supportTicketTypeEntityToModelMapper.transform(from.getSupportTicketType()));
        supportTicketModel.setSolvers(from.getSolvers().stream().map(userEntityToModelMapper::transform).collect(Collectors.toSet()));
        return supportTicketModel;
    }
}
