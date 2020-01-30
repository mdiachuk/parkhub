package ua.com.parkhub.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;

public class BookingFormDTO {

    @NotEmpty(message = "car number is mandatory")
    @Pattern(regexp = "[A-Z]{2}\\d{4}[A-Z]{2}", message = "car number has incorrect format")
    private String carNumber;
    @NotEmpty(message = "phone number is mandatory")
    @Pattern(regexp = "^380\\d{9}$", message = "phone number has incorrect format")
    private String phoneNumber;
    @Positive
    @Min(1)
    private Long slotId;
    @Positive
    private Long rangeFrom;
    @Positive
    private Long rangeTo;
    @Positive
    private Integer tariff;

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

    public Long getRangeFrom() {
        return rangeFrom;
    }

    public void setRangeFrom(Long rangeFrom) {
        this.rangeFrom = rangeFrom;
    }

    public Long getRangeTo() {
        return rangeTo;
    }

    public void setRangeTo(Long rangeTo) {
        this.rangeTo = rangeTo;
    }

    public Integer getTariff() {
        return tariff;
    }

    public void setTariff(Integer tariff) {
        this.tariff = tariff;
    }

    @Override
    public String toString() {
        return "BookingFormDTO{" +
                "carNumber='" + carNumber + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", slotId=" + slotId +
                ", rangeFrom=" + rangeFrom +
                ", rangeTo=" + rangeTo +
                ", tariff=" + tariff +
                '}';
    }
}
