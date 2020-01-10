package ua.com.parkhub.dto;

import ua.com.parkhub.validation.annotations.*;
import ua.com.parkhub.validation.groups.ManagerChecks;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class ManagerRegistrationDataDTO {

    @Valid
    private UserDTO user;

    @NotNull(message = "Company name required", groups = ManagerChecks.class)
    @NotEmpty(message = "Company name must not be empty", groups = ManagerChecks.class)
    @Size(max = 255, message = "Company name must be 255 characters at most", groups = ManagerChecks.class)
    private String companyName;

    @ValidUsreouCode(groups = ManagerChecks.class)
    @NotNull(message = "USREOU code required", groups = ManagerChecks.class)
    @NotEmpty(message = "USREOU code must not be empty", groups = ManagerChecks.class)
    private String usreouCode;

    @Size(max=200, message = "Comment must be 200 characters at most", groups = ManagerChecks.class)
    private String comment;

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public String getFirstName() {
        return user.getFirstName();
    }

    public void setFirstName(String firstName) {
        this.user.setFirstName(firstName);
    }

    public String getLastName() {
        return user.getLastName();
    }

    public void setLastName(String lastName) {
        this.user.setLastName(lastName);
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getUsreouCode() {
        return usreouCode;
    }

    public void setUsreouCode(String usreouCode) {
        this.usreouCode = usreouCode;
    }

    public String getEmail() {
        return user.getEmail();
    }

    public void setEmail(String email) {
        this.user.setEmail(email);
    }

    public String getPhoneNumber() {
        return user.getCustomer().getPhoneNumber();
    }

    public void setPhoneNumber(String phoneNumber) {
        this.user.getCustomer().setPhoneNumber(phoneNumber);
    }

    public String getPassword() {
        return this.user.getPassword();
    }

    public void setPassword(String password) {
        this.user.setPassword(password);
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
