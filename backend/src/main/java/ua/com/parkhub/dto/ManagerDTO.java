package ua.com.parkhub.dto;

import ua.com.parkhub.validation.annotations.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@PasswordMatches
public class ManagerDTO {

    @NotNull(message = "First name must not be null")
    @NotEmpty(message = "First name must not be empty")
    @Size(max = 255, message = "First name must be 255 characters at most")
    private String firstName;

    @NotNull(message = "Last name required")
    @NotEmpty(message = "Last name must not be empty")
    @Size(max = 255, message = "Last name must be 255 characters at most")
    private String lastName;

    @NotNull(message = "Company name required")
    @NotEmpty(message = "Company name must not be empty")
    @Size(max = 255, message = "Company name must be 255 characters at most")
    private String companyName;

    @ValidUsreouCode
    @NotNull(message = "USREOU code required")
    @NotEmpty(message = "USREOU code must not be empty")
    private String usreouCode;

    @ValidEmail
    @NotNull(message = "Email must required")
    @NotEmpty(message = "Email must not be empty")
    @Size(max = 255, message = "Email must be 255 characters at most")
    private String email;

    @ValidPhoneNumber
    @NotNull(message = "Phone number required")
    @NotEmpty(message = "Phone number must not be empty")
    private String phoneNumber;

    @ValidPassword
    @NotNull(message = "Password required")
    @NotEmpty(message = "Password must not be empty")
    @Size(max = 60, message = "Password must be 60 characters at most")
    private String password;

    @NotNull(message = "Matching password required")
    @NotEmpty(message = "Matching password must not be empty")
    @Size(max = 60, message = "Matching password must be 60 characters at most")
    private String matchingPassword;

    @Size(max=200, message = "Comment must be 200 characters at most")
    private String comment;

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
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
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

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
