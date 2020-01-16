package ua.com.parkhub.mappers.modelToEntity;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.com.parkhub.mappers.Mapper;
import ua.com.parkhub.model.CustomerModel;
import ua.com.parkhub.model.UserModel;
import ua.com.parkhub.model.enums.TicketTypeModel;
import ua.com.parkhub.persistence.entities.Customer;
import ua.com.parkhub.persistence.entities.SupportTicket;
import ua.com.parkhub.model.SupportTicketModel;
import ua.com.parkhub.persistence.entities.SupportTicketType;
import ua.com.parkhub.persistence.entities.User;

import java.util.stream.Collectors;

@Component
public class SupportTicketModelToEntityMapper implements Mapper<SupportTicketModel, SupportTicket> {

    private Mapper<CustomerModel, Customer> customerModelToEntityMapper;
    private Mapper<TicketTypeModel, SupportTicketType> supportTicketTypeModelToEntityMapper;
    private Mapper<UserModel, User> userModelToEntityMapper;

    @Autowired
    public SupportTicketModelToEntityMapper(Mapper<CustomerModel, Customer> customerModelToEntityMapper,
                                            Mapper<TicketTypeModel, SupportTicketType> supportTicketTypeModelToEntityMapper,
                                            Mapper<UserModel, User> userModelToEntityMapper) {
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
