package ua.com.parkhub.model;

import java.util.Objects;

public class UserRole extends AbstractModel {

    private Long id;
    private String roleName;
    private boolean isActive;

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

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserRole)) return false;

        UserRole userRole = (UserRole) o;

        if (isActive != userRole.isActive) return false;
        if (!Objects.equals(id, userRole.id)) return false;
        return Objects.equals(roleName, userRole.roleName);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (roleName != null ? roleName.hashCode() : 0);
        result = 31 * result + (isActive ? 1 : 0);
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("UserRole" + ", id: ").append(id);
        sb.append(", roleName: ").append(roleName);
        sb.append(", isActive: ").append(isActive);
        return sb.toString();
    }
}
