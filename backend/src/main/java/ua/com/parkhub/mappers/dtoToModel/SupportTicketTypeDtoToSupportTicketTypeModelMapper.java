package ua.com.parkhub.mappers.dtoToModel;

import org.springframework.stereotype.Component;
import ua.com.parkhub.dto.SupportTicketTypeDTO;
import ua.com.parkhub.mappers.Mapper;
import ua.com.parkhub.model.TicketTypeModel;

@Component
public class SupportTicketTypeDtoToSupportTicketTypeModelMapper
        implements Mapper<SupportTicketTypeDTO, TicketTypeModel> {

    @Override
    public TicketTypeModel transform(SupportTicketTypeDTO from) {
        return null;
    }
}
