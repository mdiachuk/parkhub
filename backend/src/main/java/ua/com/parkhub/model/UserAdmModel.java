package ua.com.parkhub.model;

public class UserAdmModel {
    private long id;
    private String firstName;
    private UserRoleModel userRoleModel;

    public UserAdmModel(long id, String firstName, UserRoleModel userRoleModel) {
        this.id = id;
        this.firstName = firstName;
        this.userRoleModel = userRoleModel;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public UserRoleModel getUserRoleModel() {
        return userRoleModel;
    }

    public void setUserRoleModel(UserRoleModel userRoleModel) {
        this.userRoleModel = userRoleModel;
    }
}
