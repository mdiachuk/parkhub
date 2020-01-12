package ua.com.parkhub.mappers.modelToEntity;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import ua.com.parkhub.mappers.Mapper;
import ua.com.parkhub.persistence.entities.SupportTicket;
import ua.com.parkhub.model.SupportTicketModel;

import java.util.stream.Collectors;

@Component
public class SupportTicketModelToEntityMapper implements Mapper<SupportTicketModel, SupportTicket> {

    CustomerModelToEntityMapper customerModelToEntityMapper;
    SupportTicketTypeModelToEntityMapper supportTicketTypeModelToEntityMapper;
    UserModelToEntityMapper userModelToEntityMapper;

    @Autowired
    public SupportTicketModelToEntityMapper(CustomerModelToEntityMapper customerModelToEntityMapper,
                                            SupportTicketTypeModelToEntityMapper supportTicketTypeModelToEntityMapper,
                                            @Lazy UserModelToEntityMapper userModelToEntityMapper) {
        this.customerModelToEntityMapper = customerModelToEntityMapper;
        this.supportTicketTypeModelToEntityMapper = supportTicketTypeModelToEntityMapper;
        this.userModelToEntityMapper = userModelToEntityMapper;
    }

    @Override
    public SupportTicket transform(SupportTicketModel from) {
        if (from == null) {
            return null;
        }
        SupportTicket supportTicket = new SupportTicket();
        supportTicket.setId(from.getId());
        supportTicket.setDescription(from.getDescription());
        supportTicket.setSolved(from.isSolved());
        supportTicket.setCustomer(customerModelToEntityMapper.transform(from.getCustomer()));
        supportTicket.setSupportTicketType(supportTicketTypeModelToEntityMapper.transform(from.getType()));
        supportTicket.setSolvers(from.getSolvers().stream().map(userModelToEntityMapper::transform).collect(Collectors.toList()));
        return supportTicket;
    }
}
