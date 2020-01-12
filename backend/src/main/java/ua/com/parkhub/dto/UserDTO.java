package ua.com.parkhub.dto;

import ua.com.parkhub.validation.annotations.ValidEmail;
import ua.com.parkhub.validation.annotations.ValidPassword;
import ua.com.parkhub.validation.groups.UserChecks;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UserDTO {

    private Long id;

    @Valid
    private CustomerDTO customer;

    @NotNull(message = "First name must not be null", groups = UserChecks.class)
    @NotEmpty(message = "First name must not be empty", groups = UserChecks.class)
    @Size(max = 255, message = "First name must be 255 characters at most", groups = UserChecks.class)
    private String firstName;

    @NotNull(message = "Last name required", groups = UserChecks.class)
    @NotEmpty(message = "Last name must not be empty", groups = UserChecks.class)
    @Size(max = 255, message = "Last name must be 255 characters at most", groups = UserChecks.class)
    private String lastName;

    @ValidEmail(groups = UserChecks.class)
    @NotNull(message = "Email must required", groups = UserChecks.class)
    @NotEmpty(message = "Email must not be empty", groups = UserChecks.class)
    @Size(max = 255, message = "Email must be 255 characters at most", groups = UserChecks.class)
    private String email;

    @ValidPassword(groups = UserChecks.class)
    @NotNull(message = "Password required", groups = UserChecks.class)
    @NotEmpty(message = "Password must not be empty", groups = UserChecks.class)
    @Size(max = 60, message = "Password must be 60 characters at most", groups = UserChecks.class)
    private String password;

    private RoleDTO role;
    private String token;
    private int numberOfFailedPassEntering;

    public Long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public CustomerDTO getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerDTO customer) {
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

    public RoleDTO getRole() {
        return role;
    }

    public void setRole(RoleDTO role) {
        this.role = role;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getNumberOfFailedPassEntering() {
        return numberOfFailedPassEntering;
    }

    public void setNumberOfFailedPassEntering(int numberOfFailedPassEntering) {
        this.numberOfFailedPassEntering = numberOfFailedPassEntering;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "id=" + id +
                ", customer=" + customer +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                ", token='" + token + '\'' +
                ", numberOfFailedPassEntering=" + numberOfFailedPassEntering +
                '}';
    }
}
