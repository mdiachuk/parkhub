package ua.com.parkhub.dto;

import java.util.Objects;

public class SupportTicketDTO {


    private Long id;

    private String description;

    private boolean isSolved = false;

    private TicketTypeDTO type;

    private CustomerDTO customer;

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

    public TicketTypeDTO getSupportTicketType() {
        return type;
    }

    public void setSupportTicketType(TicketTypeDTO supportTicketType) {
        this.type = supportTicketType;
    }

    public CustomerDTO getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerDTO customer) {
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
                Objects.equals(type, that.type) &&
                Objects.equals(customer, that.customer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, description, isSolved, type, customer);
    }

    @Override
    public String toString() {
        return "SupportTicketDTO{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", isSolved=" + isSolved +
                ", supportTicketType=" + type +
                ", customer=" + customer +
                '}';
    }
}
