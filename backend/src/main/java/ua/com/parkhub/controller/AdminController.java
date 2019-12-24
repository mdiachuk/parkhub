package ua.com.parkhub.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.com.parkhub.dto.AdminDTO;
import ua.com.parkhub.service.impl.AdminService;


@RestController
public class AdminController {
    public AdminService adminService;

    @Autowired
    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping("/admin/{id}")
    public AdminDTO getUserByID(@PathVariable("id")long id ){
        AdminDTO targetUserDTO = new AdminDTO();
        targetUserDTO.setUserRole(adminService.getRole(id));
        targetUserDTO.setFirstName(adminService.getFirstName(id));
        targetUserDTO.setId(adminService.getId(id));
        return targetUserDTO;
    }

    @PostMapping("/admin/{id}")
    public ResponseEntity setRole(@RequestBody AdminDTO adminDTO){
        adminService.setRole(adminDTO.getId(), adminDTO.getUserRole());
        return ResponseEntity.ok().build();
    }

}
