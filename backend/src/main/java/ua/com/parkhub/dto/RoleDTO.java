package ua.com.parkhub.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)

public enum RoleDTO {
    ADMIN("ADMIN"),
    USER("USER"),
    PENDING("PENDING"),
    MANAGER("MANAGER");

    private String role;
    private Long id;

    RoleDTO(String role) {
        this.role = role;
    }

    public String getRoleName() {
        return this.role;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
