package ua.com.parkhub.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import javax.validation.constraints.NotNull;

import javax.validation.constraints.Pattern;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "email",
        "phoneNumber"

})
public class PhoneEmailDTO {

    @NotNull
    @JsonProperty("email")
    private String email;

    @NotNull
    @Pattern(regexp="^380\\d{9}$", message="Invalid phonenumber")
    @JsonProperty("phoneNumber")
    private String phoneNumber;

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

}
