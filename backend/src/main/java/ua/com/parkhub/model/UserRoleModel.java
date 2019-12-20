package ua.com.parkhub.model;

public class UserRoleModel {

    private String roleName;
    private boolean isActive;

    public UserRoleModel(String roleName, boolean isActive) {
        this.roleName = roleName;
        this.isActive = isActive;
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
