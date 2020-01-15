package ua.com.parkhub.service;

import ua.com.parkhub.dto.AdminDTO;
import ua.com.parkhub.dto.AdminSupportTicketDTO;
import ua.com.parkhub.dto.AdminTicketCounterDTO;

import java.util.List;

public interface IAdminService {
    AdminDTO getUserById(long id);
    void setRole(long id);
    List<AdminSupportTicketDTO> getTicketsList();
    AdminSupportTicketDTO getSingleTicketById(long id);
    AdminTicketCounterDTO getTicketCounter();
}
