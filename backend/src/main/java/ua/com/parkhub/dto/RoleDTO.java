package ua.com.parkhub.dto;

public enum  RoleDTO {
    ADMIN("ADMIN"),
    USER("USER"),
    MANAGER("MANAGER");

    private String role;

    RoleDTO(String role){
        this.role = role;
    }

    public String getRoleName(){
        return this.role;
    }
}
