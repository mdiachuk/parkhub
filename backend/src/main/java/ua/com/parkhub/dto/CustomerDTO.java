package ua.com.parkhub.dto;

<<<<<<< HEAD
public class CustomerDTO {

    private long id;
    private boolean isActive;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

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

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

}
