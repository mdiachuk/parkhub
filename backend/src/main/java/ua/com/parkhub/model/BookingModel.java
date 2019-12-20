package ua.com.parkhub.model;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class BookingModel {

    private CustomerModel customer;
    private String carNumber;
    private SlotModel slot;
    private LocalDateTime checkIn;
    private LocalDateTime checkOut;
    private boolean isActive;

    public BookingModel(CustomerModel customer, String carNumber, SlotModel slot, LocalDateTime checkIn, LocalDateTime checkOut, boolean isActive) {
        this.customer = customer;
        this.carNumber = carNumber;
        this.slot = slot;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.isActive = isActive;
    }

    public CustomerModel getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerModel customer) {
        this.customer = customer;
    }

    public String getCarNumber() {
        return carNumber;
    }

    public void setCarNumber(String carNumber) {
        this.carNumber = carNumber;
    }

    public SlotModel getSlot() {
        return slot;
    }

    public void setSlot(SlotModel slot) {
        this.slot = slot;
    }

    public LocalDateTime getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(LocalDateTime checkIn) {
        this.checkIn = checkIn;
    }

    public LocalDateTime getCheckOut() {
        return checkOut;
    }

    public void setCheckOut(LocalDateTime checkOut) {
        this.checkOut = checkOut;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public int countPrice() {
        if (checkOut != null) {
            int hours = (int) ChronoUnit.HOURS.between(checkIn, checkOut);
            int tariff = slot.getParking().getTariff();
            return hours * tariff;
        } else {
            return 0;
        }
    }
}
