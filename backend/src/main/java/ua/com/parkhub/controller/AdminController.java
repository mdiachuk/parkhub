package ua.com.parkhub.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import ua.com.parkhub.persistence.entities.User;
import ua.com.parkhub.persistence.entities.UserRole;
import ua.com.parkhub.service.AdminService;

@RestController @CrossOrigin(origins = "http://localhost:4200")
public class AdminController {
    public AdminService adminService;

    @Autowired
    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping("/admin/{id}")
    public User getUserById(@PathVariable("id") long id){
        User targetUser = adminService.findUserById(id);
        return targetUser;
    }

    @PostMapping("/admin")
    public void setUserRole(long id, UserRole role){
        adminService.setRole(id, role);
    }



    /*@GetMapping("/test")
    public TestDTO test() {
//        User user = adminService.findUserById(id);
        TestDTO test = new TestDTO();
        test.setName("Vasya");
//        test.setName(user.getFirstName());
//        return test;
        return test;
    }*/
}
