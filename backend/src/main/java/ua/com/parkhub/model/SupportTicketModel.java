package ua.com.parkhub.model;

import ua.com.parkhub.persistence.entities.Customer;

public class SupportTicketModel {

    private Long id;
    private String description;
    private boolean isSolved;
    private SupportTicketTypeModel supportTicketType;
    private Customer customer;


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

    public SupportTicketTypeModel getSupportTicketType() {
        return supportTicketType;
    }

    public void setSupportTicketType(SupportTicketTypeModel supportTicketType) {
        this.supportTicketType = supportTicketType;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
