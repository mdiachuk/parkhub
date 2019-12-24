package ua.com.parkhub.dto;

import ua.com.parkhub.validation.groups.CustomerChecks;
import ua.com.parkhub.validation.annotations.ValidPhoneNumber;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class CustomerDTO {

    @ValidPhoneNumber(groups = CustomerChecks.class)
    @NotNull(message = "Phone number required", groups = CustomerChecks.class)
    @NotEmpty(message = "Phone number must not be empty", groups = CustomerChecks.class)
    private String phoneNumber;

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}