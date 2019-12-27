package ua.com.parkhub.dto;

import ua.com.parkhub.validation.annotations.ValidPhoneNumber;
import ua.com.parkhub.validation.groups.CustomerChecks;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class CustomerDTO {

    private long id;
    private boolean isActive;

    @ValidPhoneNumber(groups = CustomerChecks.class)
    @NotNull(message = "Phone number required", groups = CustomerChecks.class)
    @NotEmpty(message = "Phone number must not be empty", groups = CustomerChecks.class)
    private String phoneNumber;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
