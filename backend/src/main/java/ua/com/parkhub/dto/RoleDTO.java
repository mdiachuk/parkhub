package ua.com.parkhub.dto;

public enum  RoleDTO {
    ADMIN("ADMIN"),
    USER("USER"),
    PENDING("PENDING"),
    MANAGER("MANAGER");

    private String role;

    RoleDTO(String role){
        this.role = role;
    }

    public String getRoleName(){
        return this.role;
    }
}
