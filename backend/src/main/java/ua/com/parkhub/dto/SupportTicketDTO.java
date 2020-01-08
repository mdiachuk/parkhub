package ua.com.parkhub.dto;

import ua.com.parkhub.model.CustomerModel;
import ua.com.parkhub.persistence.entities.Customer;

import java.util.Objects;

public class SupportTicketDTO {


    private Long id;

    private String description;

    private boolean isSolved = false;

    private SupportTicketTypeDTO supportTicketType;

    private CustomerModel customer;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isSolved() {
        return isSolved;
    }

    public void setSolved(boolean solved) {
        isSolved = solved;
    }

    public SupportTicketTypeDTO getSupportTicketType() {
        return supportTicketType;
    }

    public void setSupportTicketType(SupportTicketTypeDTO supportTicketType) {
        this.supportTicketType = supportTicketType;
    }

    public CustomerModel getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerModel customer) {
        this.customer = customer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SupportTicketDTO that = (SupportTicketDTO) o;
        return isSolved == that.isSolved &&
                Objects.equals(id, that.id) &&
                Objects.equals(description, that.description) &&
                Objects.equals(supportTicketType, that.supportTicketType) &&
                Objects.equals(customer, that.customer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, description, isSolved, supportTicketType, customer);
    }

    @Override
    public String toString() {
        return "SupportTicketDTO{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", isSolved=" + isSolved +
                ", supportTicketType=" + supportTicketType +
                ", customer=" + customer +
                '}';
    }
}
