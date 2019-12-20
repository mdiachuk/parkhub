package ua.com.parkhub.persistence.entities;

import javax.persistence.Entity;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
@Table(name = "user_role", schema = "park_hub")
public class UserRole implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "roleName")
    @NotNull
    @Size(min = 3, max = 50)
    private String roleName;

    @Column
    @NotNull
    private boolean isActive = true;

    public UserRole() {
    }

    public UserRole(@NotNull @Size(min = 3, max = 50) String roleName, @NotNull boolean isActive) {
        this.roleName = roleName;
        this.isActive = isActive;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}