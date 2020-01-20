package ua.com.parkhub.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.com.parkhub.dto.AdminDTO;
import ua.com.parkhub.dto.AdminSupportTicketDTO;
import ua.com.parkhub.dto.AdminTicketCounterDTO;
import ua.com.parkhub.dto.RoleDTO;
import ua.com.parkhub.exceptions.NotFoundInDataBaseException;
import ua.com.parkhub.mappers.modelToDto.TicketSupportModelToAdminSupportTicketDTO;

import ua.com.parkhub.model.enums.RoleModel;
import ua.com.parkhub.model.SupportTicketModel;
import ua.com.parkhub.model.UserModel;
import ua.com.parkhub.model.enums.TicketTypeModel;
import ua.com.parkhub.persistence.impl.SupportTicketDAO;
import ua.com.parkhub.persistence.impl.UserDAO;
import ua.com.parkhub.persistence.impl.UserRoleDAO;
import ua.com.parkhub.service.IAdminService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AdminService implements IAdminService {
    final int LIST_ADJUSTER_START = 1;
    final int LIST_ADJUSTER_FINISH = 6;
    private UserDAO userDAO;
    private UserRoleDAO userRoleDAO;
    private SupportTicketDAO supportTicketDAO;


    public AdminService(UserDAO userDAO, UserRoleDAO userRoleDAO, SupportTicketDAO supportTicketDAO) {
        this.userDAO = userDAO;
        this.userRoleDAO = userRoleDAO;
        this.supportTicketDAO = supportTicketDAO;
    }

    public AdminDTO findUser(long id){
        Optional<UserModel> targetUser = userDAO.findElementById(id);
        return targetUser.map(target -> {
            AdminDTO targetAdminDTO = new AdminDTO();
            targetAdminDTO.setId(targetUser.get().getId());
            targetAdminDTO.setFirstName(targetUser.get().getFirstName() + " " + targetUser.get().getLastName());
            targetAdminDTO.setUserRole(targetUser.get().getRole().getRoleName());
            return targetAdminDTO;
        }).orElseThrow(() -> new NotFoundInDataBaseException("User not found"));
    }

    @Transactional
    public void updateRole(long id){
        List<RoleModel> roleList = userRoleDAO.findAll().stream().filter(role -> role.getRoleName().equals(RoleDTO.MANAGER.toString())).collect(Collectors.toList());
        Optional<UserModel> targetOptionalUser = userDAO.findElementById(id);
        UserModel targetUser = targetOptionalUser.get();
        targetUser.setRole(roleList.get(0));
        userDAO.updateElement(targetUser);
    }

    public void solveTicket(long id){
        Optional<SupportTicketModel> targetOptionalTicket = supportTicketDAO.findElementById(id);
        SupportTicketModel targetTicket = targetOptionalTicket.get();
        targetTicket.setSolved(true);
        supportTicketDAO.updateElement(targetTicket);
    }

    public List<AdminSupportTicketDTO> ticketsList(){
        List<SupportTicketModel> targetSupportList = supportTicketDAO.findAll().stream().filter(isActive -> !isActive.isSolved()).collect(Collectors.toList());
        long ticketCounter = targetSupportList.size();
        List<AdminSupportTicketDTO> adminSupportTicketDTOList = new ArrayList<>();
        if (ticketCounter>5) {
            for (long i = ticketCounter - LIST_ADJUSTER_START; i > ticketCounter - LIST_ADJUSTER_FINISH; i--) {
                AdminSupportTicketDTO adminSupportTicketDTO = new TicketSupportModelToAdminSupportTicketDTO().transform(targetSupportList.get((int) i));
                adminSupportTicketDTO.setSupportTicketType(targetSupportList.get((int) i).getType().getValue());
                adminSupportTicketDTO.setTicketHighlight(ticketHighlight(targetSupportList.get((int) i).getDescription()));
                adminSupportTicketDTOList.add(adminSupportTicketDTO);
            }
        }else {
            for(SupportTicketModel supportTicketList: targetSupportList){
                AdminSupportTicketDTO adminSupportTicketDTO = new TicketSupportModelToAdminSupportTicketDTO().transform(supportTicketList);
                adminSupportTicketDTO.setSupportTicketType(supportTicketList.getType().getValue());
                adminSupportTicketDTO.setTicketHighlight(ticketHighlight(supportTicketList.getDescription()));
                adminSupportTicketDTOList.add(adminSupportTicketDTO);
            }
        }
        return adminSupportTicketDTOList;
    }

    private String ticketHighlight(String incomingString){
        String[]array= incomingString.split(" ");
        return array[0] + " " + array[1] + " " + "...";
    }

    private long userIdFind(String incomingString){
        String[]array = incomingString.split(" ");
        return Integer.parseInt(array[1]);
    }

    public AdminSupportTicketDTO findTicket(long id){
        Optional<SupportTicketModel> targetSupportTicket = supportTicketDAO.findElementById(id);
        return targetSupportTicket.map( target -> {
            AdminSupportTicketDTO adminSupportTicketDTO = new AdminSupportTicketDTO();
            adminSupportTicketDTO.setId(target.getId());
            adminSupportTicketDTO.setDescription(target.getDescription());
            adminSupportTicketDTO.setSupportTicketType(target.getType().getValue());
            if (adminSupportTicketDTO.getSupportTicketType().equals(TicketTypeModel.MANAGER_REGISTRATION_REQUEST.getValue())){
                adminSupportTicketDTO.setTargetManagerId(userIdFind(target.getDescription()));
            }else
                adminSupportTicketDTO.setTargetManagerId(0);
            adminSupportTicketDTO.setSolved(target.isSolved());
            return adminSupportTicketDTO;
        }).orElseGet(AdminSupportTicketDTO::new);
    }

    public AdminTicketCounterDTO ticketCounter(){
        long ticketCounter = supportTicketDAO.findAll().stream().filter(isActive -> !isActive.isSolved()).count();
        AdminTicketCounterDTO targetAdminTicketCounterDTO = new AdminTicketCounterDTO();
        targetAdminTicketCounterDTO.setAdminTicketCounter(ticketCounter);
        return targetAdminTicketCounterDTO;
    }
}