package ua.com.parkhub.dto;

import java.util.List;

public class CustomerDTO {

    private long id;
    private String phoneNumber;
    private boolean isActive;
    private List<SupportTicketDTO> supportTickets;

    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    public List<SupportTicketDTO> getSupportTickets() {
        return supportTickets;
    }

    public void setSupportTickets(List<SupportTicketDTO> supportTickets) {
        this.supportTickets = supportTickets;
    }

    @Override
    public String toString() {
        return "CustomerDTO{" +
                "id=" + id +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", isActive=" + isActive +
                ", supportTickets=" + supportTickets +
                '}';
    }
}
