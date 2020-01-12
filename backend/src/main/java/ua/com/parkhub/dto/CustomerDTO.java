package ua.com.parkhub.dto;

import ua.com.parkhub.validation.annotations.ValidPhoneNumber;
import ua.com.parkhub.validation.groups.CustomerChecks;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class CustomerDTO {

    private Long id;
    private boolean isActive;

    @ValidPhoneNumber(groups = CustomerChecks.class)
    @NotNull(message = "Phone number required", groups = CustomerChecks.class)
    @NotEmpty(message = "Phone number must not be empty", groups = CustomerChecks.class)
    private String phoneNumber;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    @Override
    public String toString() {
        return "CustomerDTO{" +
                "id=" + id +
                ", isActive=" + isActive +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}
