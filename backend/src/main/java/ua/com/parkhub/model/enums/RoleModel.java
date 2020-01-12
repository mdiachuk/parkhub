package ua.com.parkhub.model.enums;

public enum RoleModel {
    ADMIN("ADMIN"),
    USER("USER"),
    PENDING("PENDING"),
    MANAGER("MANAGER");

    private String role;
    private Long id;

    RoleModel(String role){
        this.role = role;
    }

    public String getRoleName(){
        return this.role;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "RoleModel{" +
                "role='" + role + '\'' +
                ", id=" + id +
                '}';
    }

}

