package ua.com.parkhub.mappers.entityToModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import ua.com.parkhub.mappers.Mapper;
import ua.com.parkhub.model.SupportTicketModel;
import ua.com.parkhub.persistence.entities.SupportTicket;

import java.util.stream.Collectors;

@Component
public class SupportTicketEntityToModelMapper implements Mapper<SupportTicket, SupportTicketModel> {

    SupportTicketTypeEntityToModelMapper supportTicketTypeEntityToModelMapper;
    CustomerEntityToModelMapper customerEntityToModelMapper;
    UserEntityToModelMapper userEntityToModelMapper;

    @Autowired
    public SupportTicketEntityToModelMapper(SupportTicketTypeEntityToModelMapper supportTicketTypeEntityToModelMapper,
                                            CustomerEntityToModelMapper customerEntityToModelMapper,
                                            @Lazy UserEntityToModelMapper userEntityToModelMapper) {
        this.supportTicketTypeEntityToModelMapper = supportTicketTypeEntityToModelMapper;
        this.customerEntityToModelMapper = customerEntityToModelMapper;
        this.userEntityToModelMapper = userEntityToModelMapper;
    }

    @Override
    public SupportTicketModel transform(SupportTicket from) {
        if (from == null) {
            return null;
        }
        SupportTicketModel supportTicketModel = new SupportTicketModel();
        supportTicketModel.setDescription(from.getDescription());
        supportTicketModel.setId(from.getId());
        supportTicketModel.setCustomer(customerEntityToModelMapper.transform(from.getCustomer()));
        supportTicketModel.setSolved(from.isSolved());
        supportTicketModel.setType(supportTicketTypeEntityToModelMapper.transform(from.getSupportTicketType()));
        supportTicketModel.setSolvers(from.getSolvers().stream().map(userEntityToModelMapper::transform).collect(Collectors.toList()));
        return supportTicketModel;
    }
}
