package ua.com.parkhub.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum RoleDTO {
    ADMIN("ADMIN"),
    USER("USER"),
    PENDING("PENDING"),
    MANAGER("MANAGER");

    private String role;

    RoleDTO(String role) {
        this.role = role;
    }

    public String getRoleName() {
        return this.role;
    }


}
