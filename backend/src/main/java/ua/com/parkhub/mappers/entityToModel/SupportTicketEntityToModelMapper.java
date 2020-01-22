package ua.com.parkhub.mappers.entityToModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import ua.com.parkhub.mappers.Mapper;
import ua.com.parkhub.model.CustomerModel;
import ua.com.parkhub.model.SupportTicketModel;
import ua.com.parkhub.model.UserModel;
import ua.com.parkhub.model.enums.TicketTypeModel;
import ua.com.parkhub.persistence.entities.Customer;
import ua.com.parkhub.persistence.entities.SupportTicket;
import ua.com.parkhub.persistence.entities.SupportTicketType;
import ua.com.parkhub.persistence.entities.User;

import java.util.stream.Collectors;

@Component
public class SupportTicketEntityToModelMapper implements Mapper<SupportTicket, SupportTicketModel> {

    private Mapper<SupportTicketType, TicketTypeModel> supportTicketTypeEntityToModelMapper;
    private Mapper<User, UserModel> userEntityToModelMapper;
    private Mapper<Customer, CustomerModel> customerEntityToModelMapper;

    @Autowired
    public SupportTicketEntityToModelMapper( Mapper<SupportTicketType, TicketTypeModel> supportTicketTypeEntityToModelMapper,
                                            @Lazy Mapper<Customer, CustomerModel> customerEntityToModelMapper,
                                            @Lazy Mapper<User, UserModel> userEntityToModelMapper) {
        this.supportTicketTypeEntityToModelMapper = supportTicketTypeEntityToModelMapper;
        this.userEntityToModelMapper = userEntityToModelMapper;
        this.customerEntityToModelMapper = customerEntityToModelMapper;
    }

    @Override
    public SupportTicketModel transform(SupportTicket from) {
        if (from == null) {
            return null;
        }
        SupportTicketModel supportTicketModel = new SupportTicketModel();
        supportTicketModel.setDescription(from.getDescription());
        supportTicketModel.setId(from.getId());
        supportTicketModel.setSolved(from.isSolved());
        supportTicketModel.setCustomer(customerEntityToModelMapper.transform(from.getCustomer()));
        supportTicketModel.setType(supportTicketTypeEntityToModelMapper.transform(from.getSupportTicketType()));
        supportTicketModel.setSolvers(from.getSolvers().stream().map(userEntityToModelMapper::transform).collect(Collectors.toList()));
        return supportTicketModel;
    }
}
