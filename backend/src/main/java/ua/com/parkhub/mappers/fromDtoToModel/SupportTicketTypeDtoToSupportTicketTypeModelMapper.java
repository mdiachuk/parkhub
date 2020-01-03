package ua.com.parkhub.mappers.fromDtoToModel;

import org.springframework.stereotype.Component;
import ua.com.parkhub.dto.SupportTicketTypeDTO;
import ua.com.parkhub.model.Mapper;
import ua.com.parkhub.model.SupportTicketTypeModel;

@Component
public class SupportTicketTypeDtoToSupportTicketTypeModelMapper
        implements Mapper<SupportTicketTypeDTO, SupportTicketTypeModel> {

    @Override
    public SupportTicketTypeModel transform(SupportTicketTypeDTO from) {

        SupportTicketTypeModel supportTicketTypeModel = new SupportTicketTypeModel();
        supportTicketTypeModel.setId(from.getId());
        supportTicketTypeModel.setActive(from.isActive());
        supportTicketTypeModel.setType(from.getType());

        return supportTicketTypeModel;
    }
}
