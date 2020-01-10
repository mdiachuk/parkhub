package ua.com.parkhub.model;

import java.util.List;
import java.util.Objects;


public class CustomerModel extends AbstractModel {

    private Long id;
    private String phoneNumber;
    private boolean isActive = true;
    private List<BookingModel> bookings;
    private List<SupportTicketModel> supportTickets;

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

    public List<BookingModel> getBookings() {
        return bookings;
    }

    public void setBookings(List<BookingModel> bookings) {
        this.bookings = bookings;
    }

    public List<SupportTicketModel> getSupportTickets() {
        return supportTickets;
    }

    public void setSupportTickets(List<SupportTicketModel> supportTickets) {
        this.supportTickets = supportTickets;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CustomerModel that = (CustomerModel) o;
        return isActive == that.isActive &&
                Objects.equals(id, that.id) &&
                Objects.equals(phoneNumber, that.phoneNumber) &&
                Objects.equals(bookings, that.bookings) &&
                Objects.equals(supportTickets, that.supportTickets);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, phoneNumber, isActive, bookings, supportTickets);
    }

    @Override
    public String toString() {
        return "CustomerModel{" +
                "id=" + id +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", isActive=" + isActive +
                ", bookings=" + bookings +
                ", supportTickets=" + supportTickets +
                '}';
    }
}
