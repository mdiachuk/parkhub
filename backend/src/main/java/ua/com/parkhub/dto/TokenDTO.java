package ua.com.parkhub.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class TokenDTO {

    @NotNull(message = "Token required")
    @NotEmpty(message = "Token must not be empty")
    private String token;

    @NotNull(message = "Token type required")
    @NotEmpty(message = "Token type must not be empty")
    private String tokenType;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getTokenType() {
        return tokenType;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }
}
