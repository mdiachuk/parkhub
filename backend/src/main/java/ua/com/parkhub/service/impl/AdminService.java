package ua.com.parkhub.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.com.parkhub.dto.AdminSupportTicketDTO;
import ua.com.parkhub.dto.AdminTicketCounterDTO;
import ua.com.parkhub.dto.RoleDTO;
import ua.com.parkhub.mappers.modelToDto.TicketSupportModelToAdminSupportTicketDTO;
import ua.com.parkhub.model.RoleModel;
import ua.com.parkhub.model.SupportTicketModel;
import ua.com.parkhub.model.UserModel;
import ua.com.parkhub.persistence.impl.SupportTicketDAO;
import ua.com.parkhub.persistence.impl.UserDAO;
import ua.com.parkhub.persistence.impl.UserRoleDAO;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AdminService  {
    private UserDAO userDAO;
    private UserRoleDAO userRoleDAO;
    private SupportTicketDAO supportTicketDAO;


    public AdminService(UserDAO userDAO, UserRoleDAO userRoleDAO, SupportTicketDAO supportTicketDAO) {
        this.userDAO = userDAO;
        this.userRoleDAO = userRoleDAO;
        this.supportTicketDAO = supportTicketDAO;
    }

    @Transactional
    public void setRole(Long id){
        List<RoleModel> roleList = userRoleDAO.findAll().stream().filter(role -> role.getRoleName().equals(RoleDTO.MANAGER.toString())).collect(Collectors.toList());
        Optional<UserModel> targetOptionalUser = userDAO.findElementById(id);
        UserModel targetUser = targetOptionalUser.get();
        targetUser.setRole(roleList.get(0));
        userDAO.updateElement(targetUser);
    }

    public List<AdminSupportTicketDTO> getTicketsList(){
        List<SupportTicketModel> targetSupportList = supportTicketDAO.findAll().stream().filter(isActive -> !isActive.isSolved()).collect(Collectors.toList());
        long ticketCounter = targetSupportList.size();
        List<AdminSupportTicketDTO> adminSupportTicketDTOList = new ArrayList<>();
        if (ticketCounter>5) {
            for (long i = ticketCounter - 1; i > ticketCounter - 6; i--) {
                AdminSupportTicketDTO adminSupportTicketDTO = new TicketSupportModelToAdminSupportTicketDTO().transform(targetSupportList.get((int) i));
                adminSupportTicketDTO.setSupportTicketType(targetSupportList.get((int) i).getSupportTicketType().getType());
                adminSupportTicketDTO.setTicketHighlight(ticketHighlightBuilder(targetSupportList.get((int) i).getDescription()));
                adminSupportTicketDTOList.add(adminSupportTicketDTO);
            }
        }else if (ticketCounter<=5){
            for(SupportTicketModel supportTicketList: targetSupportList){
                AdminSupportTicketDTO adminSupportTicketDTO = new TicketSupportModelToAdminSupportTicketDTO().transform(supportTicketList);
                adminSupportTicketDTO.setSupportTicketType(supportTicketList.getSupportTicketType().getType());
                adminSupportTicketDTOList.add(adminSupportTicketDTO);
            }
        }
        return adminSupportTicketDTOList;
    }

    public String ticketHighlightBuilder(String incomingString){
        String[]array= incomingString.split(" ");
        String highlight = array[0] + " " + array[1] + " " + "...";
        return highlight;
    }

    public AdminSupportTicketDTO getSingleTicketById(long id){
        Optional<SupportTicketModel> targetSupportTicket = supportTicketDAO.findElementById(id);
        AdminSupportTicketDTO adminSupportTicketDTO = new AdminSupportTicketDTO();
        adminSupportTicketDTO.setId(targetSupportTicket.get().getId());
        adminSupportTicketDTO.setDescription(targetSupportTicket.get().getDescription());
        adminSupportTicketDTO.setSupportTicketType(targetSupportTicket.get().getSupportTicketType().getType());
        adminSupportTicketDTO.setSolved(targetSupportTicket.get().isSolved());
        return adminSupportTicketDTO;
    }

    public AdminTicketCounterDTO getTicketCounter(){
        long ticketCounter = supportTicketDAO.findAll().stream().filter(isActive -> !isActive.isSolved()).count();
        AdminTicketCounterDTO targetAdminTicketCounterDTO = new AdminTicketCounterDTO();
        targetAdminTicketCounterDTO.setAdminTicketCounter(ticketCounter);
        return targetAdminTicketCounterDTO;
    }
    public String getRole(long id){
        Optional<UserModel> targetUser = userDAO.findElementById(id);
        return targetUser.get().getRole().getRoleName();
    }

    public String getFullName(long id){
        Optional<UserModel> targetUser = userDAO.findElementById(id);
        String name = targetUser.get().getFirstName() + " " + targetUser.get().getLastName();
        return name;
    }

    public long getId(long id){
        Optional<UserModel> targetUser = userDAO.findElementById(id);
        return targetUser.get().getId();
    }
}