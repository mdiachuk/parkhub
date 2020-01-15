package ua.com.parkhub.mappers.modelToDto;

import ua.com.parkhub.dto.AdminSupportTicketDTO;
import ua.com.parkhub.mappers.Mapper;
import ua.com.parkhub.model.SupportTicketModel;

public class TicketSupportModelToAdminSupportTicketDTO implements Mapper<SupportTicketModel, AdminSupportTicketDTO> {

    @Override
    public AdminSupportTicketDTO transform(SupportTicketModel from){
        AdminSupportTicketDTO adminSupportTicketDTO = new AdminSupportTicketDTO();
        adminSupportTicketDTO.setId(from.getId());
        adminSupportTicketDTO.setDescription(from.getDescription());
        adminSupportTicketDTO.setSolved(from.isSolved());
        return  adminSupportTicketDTO;
    }
}
