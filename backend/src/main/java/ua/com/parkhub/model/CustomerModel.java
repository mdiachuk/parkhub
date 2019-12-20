package ua.com.parkhub.model;

public class CustomerModel {

    private String phoneNumber;
    private boolean isActive;

    public CustomerModel(String phoneNumber, boolean isActive) {
        this.phoneNumber = phoneNumber;
        this.isActive = isActive;
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
