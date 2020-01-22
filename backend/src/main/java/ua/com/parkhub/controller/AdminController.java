package ua.com.parkhub.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ua.com.parkhub.dto.AdminDTO;
import ua.com.parkhub.dto.AdminSupportTicketDTO;
import ua.com.parkhub.dto.AdminTicketCounterDTO;
import ua.com.parkhub.service.IAdminService;

import java.util.List;


@RestController
public class AdminController {
    private IAdminService adminService;

    @Autowired
    public AdminController(IAdminService adminService) {
        this.adminService = adminService;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/api/admin/manager/{id}")
    public ResponseEntity<AdminDTO> findUser(@PathVariable("id")long id ){
        return ResponseEntity.ok(adminService.findUser(id));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/api/admin/manager/{id}")
    public ResponseEntity updateRole(@RequestBody AdminDTO adminDTO){
        adminService.updateRole(adminDTO.getId());
        return ResponseEntity.accepted().build();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/api/admin/ticketlist")
    public ResponseEntity<List<AdminSupportTicketDTO>> retrieveTickets(){
        return ResponseEntity.ok(adminService.retrieveTickets());
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/api/admin/ticket/{id}")
    public ResponseEntity<AdminSupportTicketDTO> findTicket(@PathVariable("id")long id){
        return ResponseEntity.ok(adminService.findTicket(id));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/api/admin/ticket/setsolved/{id}")
    public ResponseEntity solveTicket(@RequestBody AdminSupportTicketDTO adminSupportTicketDTO){
        adminService.solveTicket(adminSupportTicketDTO.getId());
        return ResponseEntity.accepted().build();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/api/admin/ticketlistcounter")
    public ResponseEntity<AdminTicketCounterDTO> countTickets(){
        return ResponseEntity.ok(adminService.countTickets());
    }
}