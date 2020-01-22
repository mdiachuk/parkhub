package ua.com.parkhub.mappers.entityToModel;

import org.springframework.stereotype.Component;
import ua.com.parkhub.exceptions.ParkHubException;
import ua.com.parkhub.mappers.Mapper;
import ua.com.parkhub.model.enums.TicketTypeModel;
import ua.com.parkhub.persistence.entities.SupportTicketType;

import java.util.Arrays;

@Component
public class SupportTicketTypeEntityToModelMapper implements Mapper<SupportTicketType, TicketTypeModel> {

    @Override
    public TicketTypeModel transform(SupportTicketType from) {
        if (from == null) {
            return null;
        }
        String type = from.getType();
        TicketTypeModel ticketTypeModel;
        switch (type) {
            case "Manager registration request":
                ticketTypeModel = TicketTypeModel.MANAGER_REGISTRATION_REQUEST;
                ticketTypeModel.setId(from.getId());
                break;
            default:
                throw new ParkHubException(String.format("Not known support ticket type: %s. Ticket type may be one of the following: %s).", type, Arrays.asList(TicketTypeModel.values())));
        }
        return ticketTypeModel;
    }
}
