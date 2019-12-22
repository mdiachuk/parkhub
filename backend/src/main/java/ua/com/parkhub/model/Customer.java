package ua.com.parkhub.model;

import java.util.List;
import java.util.Objects;

public class Customer extends AbstractModel {

    private Long id;
    private String phoneNumber;
    private boolean isActive = true;
    //TODO ask Dima about equals
    private List<Booking> bookings;
    private List<SupportTicket> supportTickets;

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

    public List<Booking> getBookings() {
        return bookings;
    }

    public void setBookings(List<Booking> bookings) {
        this.bookings = bookings;
    }

    public List<SupportTicket> getSupportTickets() {
        return supportTickets;
    }

    public void setSupportTickets(List<SupportTicket> supportTickets) {
        this.supportTickets = supportTickets;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Customer)) return false;

        Customer customer = (Customer) o;

        if (!Objects.equals(id, customer.id)) return false;
        return Objects.equals(phoneNumber, customer.phoneNumber);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (phoneNumber != null ? phoneNumber.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Customer" + ", id: ").append(id);
        sb.append(", phoneNumber: ").append(phoneNumber);
        sb.append(", isActive: ").append(isActive);
        //TODO ask Dima about cyclic dependence
        /*sb.append(", bookings: " + bookings);
        sb.append(", supportTickets: " + supportTickets);*/
        return sb.toString();

    }
}
