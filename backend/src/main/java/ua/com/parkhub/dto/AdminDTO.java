package ua.com.parkhub.dto;

import ua.com.parkhub.service.AdminService;

public class AdminDTO {
    public String firstName;
    public String userRole;

    public AdminService adminService;

    public AdminDTO(AdminService adminService) {
        this.adminService = adminService;
    }

    public String getFirstName(long id) {

        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getUserRole(long id) {
        userRole = adminService.getRole(id);
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }
}
