package ua.com.parkhub.service;

import ua.com.parkhub.dto.AdminDTO;
import ua.com.parkhub.dto.AdminSupportTicketDTO;
import ua.com.parkhub.dto.AdminTicketCounterDTO;

import java.util.List;

public interface IAdminService {
    AdminDTO findUser(long id);
    void updateRole(long id);
    void solveTicket(long id);
    List<AdminSupportTicketDTO> retrieveTickets();
    AdminSupportTicketDTO findTicket(long id);
    AdminTicketCounterDTO countTickets();
}