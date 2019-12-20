package ua.com.parkhub.dto;

import ua.com.parkhub.validation.annotations.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@PasswordMatches
public class ManagerDTO {

    @NotNull
    @NotEmpty
    @Size(min = 1, max = 50)
    private String firstName;

    @NotNull
    @NotEmpty
    @Size(min=1, max=50)
    private String lastName;

    @NotNull
    @NotEmpty
    @Size(min=1, max=50)
    private String companyName;

    @ValidUsreouCode
    @NotNull
    @NotEmpty
    private String usreouCode;

    @ValidEmail
    @NotNull
    @NotEmpty
    private String email;

    @ValidPhoneNumber
    @NotNull
    @NotEmpty
    private String phoneNumber;

    @ValidPassword
    @NotNull
    @NotEmpty
    private String password;

    @NotNull
    @NotEmpty
    private String matchingPassword;

    @Size(max=200)
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
