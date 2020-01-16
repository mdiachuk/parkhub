package ua.com.parkhub.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    @GetMapping("/api/admin/{id}")
    public ResponseEntity<AdminDTO> getUserByID(@PathVariable("id")long id ){
        return ResponseEntity.ok(adminService.getUserById(id));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/api/admin/{id}")
    public ResponseEntity setRole(@RequestBody AdminDTO adminDTO){
        adminService.setRole(adminDTO.getId());
        return ResponseEntity.ok().build();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/api/admin/ticketlist")
    public ResponseEntity<List<AdminSupportTicketDTO>> getTicketList(){
        return ResponseEntity.ok(adminService.getTicketsList());
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/api/admin/ticket/{id}")
    public ResponseEntity<AdminSupportTicketDTO> getTicketById(@PathVariable("id")long id){
        return ResponseEntity.ok(adminService.getSingleTicketById(id));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/api/admin/ticketlistcounter")
    public ResponseEntity<AdminTicketCounterDTO> getTicketCounter(){
        return ResponseEntity.ok(adminService.getTicketCounter());
    }
}
