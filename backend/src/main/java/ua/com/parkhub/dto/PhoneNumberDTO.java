package ua.com.parkhub.dto;

import java.io.Serializable;

public class PhoneNumberDTO implements Serializable {

    private String phoneNumber;

    public PhoneNumberDTO() {
    }

    public PhoneNumberDTO(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
