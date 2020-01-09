package ua.com.parkhub.mappers.dtoToModel;

import org.springframework.stereotype.Component;
import ua.com.parkhub.dto.SupportTicketDTO;
import ua.com.parkhub.mappers.Mapper;
import ua.com.parkhub.model.SupportTicketModel;

@Component
public class SupportTicketDtoToSupportTicketModelMapper implements Mapper<SupportTicketDTO, SupportTicketModel> {

    private final TicketTypeDtoToTicketTypeModel ticketTypeDtoToTicketTypeModel;
    private final CustomerDtoToCustomerModelMapper customerDtoToCustomerModelMapper;

    public SupportTicketDtoToSupportTicketModelMapper(
            TicketTypeDtoToTicketTypeModel ticketTypeDtoToTicketTypeModel,
            CustomerDtoToCustomerModelMapper customerDtoToCustomerModelMapper) {
        this.ticketTypeDtoToTicketTypeModel = ticketTypeDtoToTicketTypeModel;
        this.customerDtoToCustomerModelMapper = customerDtoToCustomerModelMapper;
    }

    @Override
    public SupportTicketModel transform(SupportTicketDTO from) {

        SupportTicketModel supportTicketModel = new SupportTicketModel();
        supportTicketModel.setId(from.getId());
        supportTicketModel.setDescription(from.getDescription());
        supportTicketModel.setSolved(from.isSolved());
        supportTicketModel.setCustomer(customerDtoToCustomerModelMapper.transform(from.getCustomer()));
        supportTicketModel.setType(
                ticketTypeDtoToTicketTypeModel.transform(from.getSupportTicketType()));

        return supportTicketModel;
    }
}
