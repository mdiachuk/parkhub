package ua.com.parkhub.mappers.dtoToModel;

import org.springframework.stereotype.Component;
import ua.com.parkhub.dto.TicketTypeDTO;
import ua.com.parkhub.exceptions.ParkHubException;
import ua.com.parkhub.mappers.Mapper;
import ua.com.parkhub.model.enums.TicketTypeModel;

import java.util.Arrays;

@Component
public class TicketTypeDtoToTicketTypeModel implements Mapper<TicketTypeDTO, TicketTypeModel> {

    @Override
    public TicketTypeModel transform(TicketTypeDTO from) {
        TicketTypeModel ticketTypeModel;
        switch (from) {
            case MANAGER_REGISTRATION_REQUEST:
                ticketTypeModel = TicketTypeModel.MANAGER_REGISTRATION_REQUEST;
                ticketTypeModel.setId(from.getId());
                break;
            default:
                throw new ParkHubException(String.format("Not known ticket type name: %s. Type may be one of the following: %s).", from.getType(), Arrays.asList(TicketTypeModel.values())));
        }
        return ticketTypeModel;
    }
}
