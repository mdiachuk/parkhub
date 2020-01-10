package ua.com.parkhub.model;

import java.util.List;

public class SupportTicketModel {

    private Long id;
    private String description;
    private boolean isSolved;
    private TicketTypeModel type;
    private CustomerModel customer;
    private List<UserModel> solvers;

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

    public TicketTypeModel getType() {
        return type;
    }

    public void setType(TicketTypeModel type) {
        this.type = type;
    }

    public CustomerModel getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerModel customer) {
        this.customer = customer;
    }

    public List<UserModel> getSolvers() {
        return solvers;
    }

    public void setSolvers(List<UserModel> solvers) {
        this.solvers = solvers;
    }
}
