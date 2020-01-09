package ua.com.parkhub.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

    @GetMapping("/admin/{id}")
    public AdminDTO getUserByID(@PathVariable("id")long id ){
        AdminDTO targetUserDTO = new AdminDTO();
        targetUserDTO.setUserRole(adminService.getRole(id));
        targetUserDTO.setFirstName(adminService.getFullName(id));
        targetUserDTO.setId(adminService.getId(id));
        return targetUserDTO;
    }

    @PostMapping("/admin/{id}")
    public ResponseEntity setRole(@RequestBody AdminDTO adminDTO){
        adminService.setRole(adminDTO.getId());
        return ResponseEntity.ok().build();
    }

    @GetMapping("/admin/ticketlist")
    public List<AdminSupportTicketDTO> getTicketList(){
        return adminService.getTicketsList();
    }

    @GetMapping("/admin/ticket/{id}")
    public AdminSupportTicketDTO getTicketById(@PathVariable("id")long id){
        return adminService.getSingleTicketById(id);
    }

    @GetMapping("/admin/ticketlistcounter")
    public AdminTicketCounterDTO getTicketCounter(){
        return adminService.getTicketCounter();
    }
}
