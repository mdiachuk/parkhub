package ua.com.parkhub.dto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.parkhub.persistence.entities.User;
import ua.com.parkhub.service.AdminService;

@Service
public class AdminDTO {
    private long id;
    private String firstName;
    private String userRole;

    public AdminDTO() {
    }

    public AdminDTO(long id, String firstName, String userRole) {
        this.id = id;
        this.firstName = firstName;
        this.userRole = userRole;
    }

    public AdminService adminService;
    @Autowired
    public AdminDTO(AdminService adminService) {
        this.adminService = adminService;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    public AdminDTO findUserByIdDTO(long id){
        User userObj = adminService.findUserById(id);
        AdminDTO userDTO = new AdminDTO(userObj.getId(), userObj.getFirstName(),userObj.getRole().getRoleName());
        return userDTO;
    }
}