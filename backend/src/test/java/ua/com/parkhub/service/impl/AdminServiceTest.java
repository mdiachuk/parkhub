package ua.com.parkhub.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import ua.com.parkhub.model.SupportTicketModel;
import ua.com.parkhub.model.UserModel;
import ua.com.parkhub.model.enums.RoleModel;
import ua.com.parkhub.model.enums.TicketTypeModel;
import ua.com.parkhub.persistence.impl.SupportTicketDAO;
import ua.com.parkhub.persistence.impl.UserDAO;
import ua.com.parkhub.persistence.impl.UserRoleDAO;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyLong;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AdminServiceTest {
    @Mock
    private UserDAO userDAO;
    @Mock
    private UserRoleDAO userRoleDAO;
    @Mock
    private SupportTicketDAO supportTicketDAO;
    @InjectMocks
    private AdminService adminService;


    @BeforeEach
    public void init(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void findUserSuccessCase() {
        UserModel user = new UserModel();
        user.setId(1L);
        user.setFirstName("Test");
        user.setLastName("Test");
        user.setRole(RoleModel.PENDING);
        when(userDAO.findElementById(anyLong())).thenReturn(Optional.of(user));
        assertEquals(user.getId(), adminService.findUser(1L).getId());
    }

    @Test
    void updateRoleSuccessCase() {
        UserModel userModelExample = new UserModel();
        userModelExample.setId(1L);
        userModelExample.setRole(RoleModel.PENDING);

        RoleModel userRoleExpected = RoleModel.MANAGER;
        userRoleExpected.setId(1L);

        List<RoleModel> testList = new ArrayList<>();

        testList.add(userRoleExpected);

        Mockito.when(userDAO.findElementById(anyLong())).thenReturn(Optional.of(userModelExample));
        Mockito.when(userRoleDAO.findAll()).thenReturn(testList);
        adminService.updateRole(1L);

        verify(userDAO, times(1)).findElementById(1L);
    }

    @Test
    void solveTicketSuccessCase() {
        SupportTicketModel supportTicketModelExample = new SupportTicketModel();
        supportTicketModelExample.setId(1L);
        supportTicketModelExample.setSolved(false);

        SupportTicketModel supportTicketModelExpected = new SupportTicketModel();
        supportTicketModelExpected.setSolved(true);

        Mockito.when(supportTicketDAO.findElementById(1L)).thenReturn(Optional.of(supportTicketModelExample));
        adminService.solveTicket(1L);

        verify(supportTicketDAO, times(1)).findElementById(1L);
    }

    @Test
    void retrieveTicketsSuccessCase() {
        SupportTicketModel supportTicketModelId1 = new SupportTicketModel();
        supportTicketModelId1.setId(1L);
        supportTicketModelId1.setType(TicketTypeModel.MANAGER_REGISTRATION_REQUEST);
        supportTicketModelId1.setDescription("ID: 4 Company: \"Booking\" USREOU: 33333311 Comment: \"'");

        SupportTicketModel supportTicketModelId2 = new SupportTicketModel();
        supportTicketModelId2.setId(2L);
        supportTicketModelId2.setType(TicketTypeModel.MANAGER_REGISTRATION_REQUEST);
        supportTicketModelId2.setDescription("ID: 4 Company: \"Booking\" USREOU: 33333311 Comment: \"'");

        SupportTicketModel supportTicketModelId3 = new SupportTicketModel();
        supportTicketModelId3.setId(3L);
        supportTicketModelId3.setType(TicketTypeModel.MANAGER_REGISTRATION_REQUEST);
        supportTicketModelId3.setDescription("ID: 4 Company: \"Booking\" USREOU: 33333311 Comment: \"'");

        List<SupportTicketModel> targetSupportTicketTestList = new ArrayList<>();
        targetSupportTicketTestList.add(supportTicketModelId1);
        targetSupportTicketTestList.add(supportTicketModelId2);
        targetSupportTicketTestList.add(supportTicketModelId3);

        when(supportTicketDAO.findAll()).thenReturn(targetSupportTicketTestList);

        assertEquals(targetSupportTicketTestList.size(), adminService.retrieveTickets().size());
    }

    @Test
    void findTicketSuccessCase() {
        SupportTicketModel supportTicketModel = new SupportTicketModel();
        supportTicketModel.setId(1L);
        supportTicketModel.setType(TicketTypeModel.MANAGER_REGISTRATION_REQUEST);
        supportTicketModel.setDescription("ID: 4 Company: \"Booking\" USREOU: 33333311 Comment: \"'");

        Mockito.when(supportTicketDAO.findElementById(1L)).thenReturn(Optional.of(supportTicketModel));

        assertEquals(supportTicketModel.getId(), adminService.findTicket(1L).getId());
    }

    @Test
    void countTicketsSuccessCase() {
        SupportTicketModel supportTicket1 = new SupportTicketModel();
        supportTicket1.setId(1L);
        supportTicket1.setSolved(false);
        SupportTicketModel supportTicket2 = new SupportTicketModel();
        supportTicket2.setId(2L);
        supportTicket2.setSolved(false);
        SupportTicketModel supportTicket3 = new SupportTicketModel();
        supportTicket3.setId(3L);
        supportTicket3.setSolved(false);
        List<SupportTicketModel> supportTickets = new ArrayList<>();
        supportTickets.add(supportTicket1);
        supportTickets.add(supportTicket2);
        supportTickets.add(supportTicket3);

        Mockito.when(supportTicketDAO.findAll()).thenReturn(supportTickets);

        assertEquals(3, adminService.countTickets().getAdminTicketCounter());
    }
}