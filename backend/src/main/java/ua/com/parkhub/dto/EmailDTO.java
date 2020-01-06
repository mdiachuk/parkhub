package ua.com.parkhub.dto;

import ua.com.parkhub.validation.annotations.ValidEmail;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class EmailDTO {

    @ValidEmail
    @NotNull(message = "Email required")
    @NotEmpty(message = "Email must not be empty")
    @Size(max = 255, message = "Email must be 255 characters at most")
    private String email;

    private String tokenType;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTokenType() {
        return tokenType;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }
}