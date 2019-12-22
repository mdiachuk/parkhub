package ua.com.parkhub.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;
import java.io.Serializable;

public class BookingFormDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotEmpty(message="car number is mandatory")
    @Pattern(regexp ="[A-Z]{2}\\d{4}[A-Z]{2}", message="car number has incorrect format")
    private String carNumber;
    @NotEmpty(message="phone number is mandatory")
    @Pattern(regexp ="^\\+380\\d{9}$", message = "phone number has incorrect format")
    private String phoneNumber;
    @Positive
    private Long slotId;

    public String getCarNumber() {
        return carNumber;
    }

    public void setCarNumber(String carNumber) {
        this.carNumber = carNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Long getSlotId() {
        return slotId;
    }

    public void setSlotId(Long slotId) {
        this.slotId = slotId;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Slot" + ", slotId: " + slotId);
        sb.append(", carNumber: " + carNumber);
        sb.append(", phoneNumber: " + phoneNumber);
        return sb.toString();
    }
}
