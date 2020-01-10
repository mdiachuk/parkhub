package ua.com.parkhub.model;

import java.util.List;

public class CustomerModel {

    private Long id;
    private String phoneNumber;
    private boolean isActive = true;
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

    public List<SupportTicketModel> getSupportTickets() {
        return supportTickets;
    }

    public void setSupportTickets(List<SupportTicketModel> supportTickets) {
        this.supportTickets = supportTickets;
    }
}
