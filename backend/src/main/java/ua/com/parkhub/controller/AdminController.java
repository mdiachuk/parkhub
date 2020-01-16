package ua.com.parkhub.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ua.com.parkhub.dto.AdminDTO;
import ua.com.parkhub.dto.AdminSupportTicketDTO;
import ua.com.parkhub.dto.AdminTicketCounterDTO;
import ua.com.parkhub.service.impl.AdminService;

import java.util.List;


@RestController
public class AdminController {
    private AdminService adminService;

    @Autowired
    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/api/admin/{id}")
    public AdminDTO getUserByID(@PathVariable("id")long id ){
        return adminService.getUserById(id);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/api/admin/{id}")
    public ResponseEntity setRole(@RequestBody AdminDTO adminDTO){
        adminService.setRole(adminDTO.getId());
        return ResponseEntity.ok().build();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/api/admin/ticketlist")
    public List<AdminSupportTicketDTO> getTicketList(){
        return adminService.getTicketsList();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/api/admin/ticket/{id}")
    public AdminSupportTicketDTO getTicketById(@PathVariable("id")long id){
        return adminService.getSingleTicketById(id);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/api/admin/ticketlistcounter")
    public AdminTicketCounterDTO getTicketCounter(){
        return adminService.getTicketCounter();
    }
}
