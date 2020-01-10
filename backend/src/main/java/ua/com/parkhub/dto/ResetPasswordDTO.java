package ua.com.parkhub.dto;

import ua.com.parkhub.validation.annotations.ValidPassword;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class ResetPasswordDTO {

    @NotNull(message = "Token required")
    @NotEmpty(message = "Token must not be empty")
    @Size(min = 36, max = 36, message = "Token must be 36 characters long")
    private String token;

    @ValidPassword
    @NotNull(message = "Password required")
    @NotEmpty(message = "Password must not be empty")
    @Size(max = 60, message = "Password must be 60 characters at most")
    private String password;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
