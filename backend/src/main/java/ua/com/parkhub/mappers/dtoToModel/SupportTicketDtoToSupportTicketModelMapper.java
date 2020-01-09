package ua.com.parkhub.mappers.dtoToModel;

import org.springframework.stereotype.Component;
import ua.com.parkhub.dto.SupportTicketDTO;
import ua.com.parkhub.mappers.Mapper;
import ua.com.parkhub.model.SupportTicketModel;

@Component
public class SupportTicketDtoToSupportTicketModelMapper implements Mapper<SupportTicketDTO, SupportTicketModel> {

    private final SupportTicketTypeDtoToSupportTicketTypeModelMapper supportTicketTypeDtoToSupportTicketTypeModelMapper;

    public SupportTicketDtoToSupportTicketModelMapper(
            SupportTicketTypeDtoToSupportTicketTypeModelMapper supportTicketTypeDtoToSupportTicketTypeModelMapper) {
        this.supportTicketTypeDtoToSupportTicketTypeModelMapper = supportTicketTypeDtoToSupportTicketTypeModelMapper;
    }

    @Override
    public SupportTicketModel transform(SupportTicketDTO from) {

        SupportTicketModel supportTicketModel = new SupportTicketModel();
        supportTicketModel.setId(from.getId());
        supportTicketModel.setDescription(from.getDescription());
        supportTicketModel.setSolved(from.isSolved());
        supportTicketModel.setCustomer(from.getCustomer());
        supportTicketModel.setSupportTicketType(
                supportTicketTypeDtoToSupportTicketTypeModelMapper.transform(from.getSupportTicketType()));

        return supportTicketModel;
    }
}
