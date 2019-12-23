package ua.com.parkhub.model;

public class UserModel {

    private CustomerModel customer;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private UserRoleModel userRole;

    public CustomerModel getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerModel customer) {
        this.customer = customer;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserRoleModel getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRoleModel userRole) {
        this.userRole = userRole;
    }
}
