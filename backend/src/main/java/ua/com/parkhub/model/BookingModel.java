package ua.com.parkhub.model;

import java.time.LocalDateTime;

public class BookingModel {

    private Long id;
    private String carNumber;
    private LocalDateTime checkIn;
    private LocalDateTime checkOut;
    private boolean isActive;
    private CustomerModel customer;
    private SlotModel slot;

    public void setCustomer(CustomerModel customer) {
        this.customer = customer;
    }

    public void setSlot(SlotModel slot) {
        this.slot = slot;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCarNumber() {
        return carNumber;
    }

    public void setCarNumber(String carNumber) {
        this.carNumber = carNumber;
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
}
