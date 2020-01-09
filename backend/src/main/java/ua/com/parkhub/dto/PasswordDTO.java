package ua.com.parkhub.dto;

import ua.com.parkhub.validation.annotations.ValidPassword;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class PasswordDTO {

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

//package ua.com.parkhub.dto;
//
//import org.springframework.stereotype.Component;
//
//@Component
//public class PasswordDTO {
//    long id;
//    private String password;
//    private String newPassword;
//
//    public long getId() {
//        return id;
//    }
//
//    public void setId(long id) {
//        this.id = id;
//    }
//
//    public String getNewPassword() {
//        return newPassword;
//    }
//
//    public void setNewPassword(String newPassword) {
//        this.newPassword = newPassword;
//    }
//
//    public String getPassword() {
//        return password;
//    }
//
//    public void setPassword(String password) {
//        this.password = password;
//    }
//}