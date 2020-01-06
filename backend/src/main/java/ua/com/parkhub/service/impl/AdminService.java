package ua.com.parkhub.service.impl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.com.parkhub.dto.AdminSupportTicketDTO;
import ua.com.parkhub.dto.AdminTicketCounterDTO;
import ua.com.parkhub.dto.RoleDTO;
import ua.com.parkhub.mappers.ModelToDto.TicketSupportModelToAdminSupportTicketDTO;
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
        List<RoleModel> r = userRoleDAO.findAll().stream().filter(ro -> ro.getRoleName().equals(RoleDTO.MANAGER.toString())).collect(Collectors.toList());
        Optional<UserModel> targetOptionalUser = userDAO.findElementById(id);
        UserModel targetUser = targetOptionalUser.get();
        targetUser.setRole(r.get(0));
        userDAO.updateElement(targetUser);
    }

    public List<AdminSupportTicketDTO> getTicketsList(){
        List<SupportTicketModel> targetSupportList = supportTicketDAO.findAll().stream().filter(isAct -> !isAct.isSolved()).collect(Collectors.toList());
        long ticketCounter = targetSupportList.size();
        List<AdminSupportTicketDTO> adminSupportTicketDTOList = new ArrayList<>();
        for(long i=ticketCounter-1; i>ticketCounter-6; i--){
            AdminSupportTicketDTO adminSupportTicketDTO = new TicketSupportModelToAdminSupportTicketDTO().transform(targetSupportList.get((int) i));
            adminSupportTicketDTO.setSupportTicketType(targetSupportList.get((int) i).getSupportTicketType().getType());
            adminSupportTicketDTOList.add(adminSupportTicketDTO);
        }
        return adminSupportTicketDTOList;
    }

    public AdminTicketCounterDTO getTicketCounter(){
        long ticketCounter = supportTicketDAO.findAll().stream().filter(isAct -> !isAct.isSolved()).count();
        AdminTicketCounterDTO targetAdminTicketCounterDTO = new AdminTicketCounterDTO();
        targetAdminTicketCounterDTO.setAdminTicketCounter(ticketCounter);
        return targetAdminTicketCounterDTO;
    }
    public String getRole(long id){
        Optional<UserModel> targetUser = userDAO.findElementById(id);
        return targetUser.get().getRole().getRoleName();
    }

    public String getFirstName(long id){
        Optional<UserModel> targetUser = userDAO.findElementById(id);
        return targetUser.get().getFirstName();
    }

    public long getId(long id){
        Optional<UserModel> targetUser = userDAO.findElementById(id);
        return targetUser.get().getId();
    }
}