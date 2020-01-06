package ua.com.parkhub.model;

public enum RoleModel {
    ADMIN("ADMIN"),
    USER("USER"),
    PENDING("PENDING"),
    MANAGER("MANAGER");

    private String role;

    RoleModel(String role){
        this.role = role;
    }

    public String getRoleName(){
        return this.role;
    }
}

