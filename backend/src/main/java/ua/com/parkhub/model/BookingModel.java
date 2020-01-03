package ua.com.parkhub.model;

import java.time.LocalDateTime;

public class BookingModel {

    private Long id;
    private CustomerModel customer;
    private String carNumber;
    private SlotModel slot;
    private LocalDateTime checkIn;
    private LocalDateTime checkOut;
    private boolean isActive;

    public CustomerModel getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerModel customer) {
        this.customer = customer;
    }

    public SlotModel getSlot() {
        return slot;
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

//    public int countPrice() {
//        if (checkOut != null) {
//            int hours = (int) ChronoUnit.HOURS.between(checkIn, checkOut);
//            int tariff = slot.getParking().getTariff();
//            return hours * tariff;
//        } else {
//            return 0;
//        }
//    }
}
