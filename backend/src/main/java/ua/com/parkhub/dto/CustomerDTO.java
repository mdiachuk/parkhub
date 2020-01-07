package ua.com.parkhub.dto;

import java.util.List;

public class CustomerDTO {

    private Long id;
    private String phoneNumber;
    private boolean isActive;
    private List<SupportTicketDTO> supportTickets;

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
