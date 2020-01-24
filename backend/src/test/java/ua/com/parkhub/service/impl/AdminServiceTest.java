package ua.com.parkhub.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import ua.com.parkhub.persistence.impl.SupportTicketDAO;
import ua.com.parkhub.persistence.impl.UserDAO;
import ua.com.parkhub.persistence.impl.UserRoleDAO;

import static org.junit.jupiter.api.Assertions.*;

class AdminServiceTest {
    @Mock
    private UserDAO userDAO;
    @Mock
    private UserRoleDAO userRoleDAO;
    @Mock
    private SupportTicketDAO supportTicketDAO;

    @InjectMocks
    AdminService adminService;

    @BeforeEach
    public void init(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void findUser() {
    }

    @Test
    void updateRole() {
    }

    @Test
    void solveTicket() {
    }

    @Test
    void retrieveTickets() {
    }

    @Test
    void findTicket() {
    }

    @Test
    void countTickets() {
    }
}