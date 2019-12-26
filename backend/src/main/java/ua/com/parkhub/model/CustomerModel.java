package ua.com.parkhub.model;

import java.util.Set;

public class CustomerModel {

    private Long id;
    private String phoneNumber;
    private boolean isActive = true;
    private Set<BookingModel> bookings;
    private Set<SupportTicketModel> supportTickets;

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

    public Set<BookingModel> getBookings() {
        return bookings;
    }

    public void setBookings(Set<BookingModel> bookings) {
        this.bookings = bookings;
    }

    public Set<SupportTicketModel> getSupportTickets() {
        return supportTickets;
    }

    public void setSupportTickets(Set<SupportTicketModel> supportTickets) {
        this.supportTickets = supportTickets;
    }
}
