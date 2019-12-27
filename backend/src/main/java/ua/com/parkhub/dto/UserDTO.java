package ua.com.parkhub.dto;

import ua.com.parkhub.validation.annotations.*;
import ua.com.parkhub.validation.groups.UserChecks;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@PasswordMatches(groups = UserChecks.class)
public class UserDTO {

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

    @NotNull(message = "Matching password required")
    @NotEmpty(message = "Matching password must not be empty")
    @Size(max = 60, message = "Matching password must be 60 characters at most")
    private String matchingPassword;

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

    public String getPhoneNumber() {
        return customer.getPhoneNumber();
    }

    public void setPhoneNumber(String phoneNumber) {
        this.customer.setPhoneNumber(phoneNumber);
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMatchingPassword() {
        return matchingPassword;
    }

    public void setMatchingPassword(String matchingPassword) {
        this.matchingPassword = matchingPassword;
    }
}