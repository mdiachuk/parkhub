
package ua.com.parkhub.model;

import java.time.LocalDateTime;
import java.util.Objects;

public class Booking extends AbstractModel {

    private Long id;
    private String carNumber;
    private LocalDateTime checkIn;
    private LocalDateTime checkOut;
    private boolean isActive;
    private CustomerModel customer;
    private SlotModel slot;

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

    public void setCheck_out(LocalDateTime checkOut) {
        this.checkOut = checkOut;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

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

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Booking)) return false;

        Booking booking = (Booking) o;

        if (isActive != booking.isActive) return false;
        if (!Objects.equals(id, booking.id)) return false;
        if (!Objects.equals(carNumber, booking.carNumber)) return false;
        if (!Objects.equals(checkIn, booking.checkIn)) return false;
        if (!Objects.equals(checkOut, booking.checkOut)) return false;
        if (!Objects.equals(customer, booking.customer)) return false;
        return Objects.equals(slot, booking.slot);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (carNumber != null ? carNumber.hashCode() : 0);
        result = 31 * result + (checkIn != null ? checkIn.hashCode() : 0);
        result = 31 * result + (checkOut != null ? checkOut.hashCode() : 0);
        result = 31 * result + (isActive ? 1 : 0);
        result = 31 * result + (customer != null ? customer.hashCode() : 0);
        result = 31 * result + (slot != null ? slot.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Booking" + ", id: ").append(id);
        sb.append(", carNumber: ").append(carNumber);
        sb.append(", checkIn: ").append(checkIn);
        sb.append(", checkOut: ").append(checkOut);
        sb.append(", isActive: ").append(isActive);
        sb.append(", customer: ").append(customer);
        sb.append(", slot: ").append(slot);
        return sb.toString();
    }
}
