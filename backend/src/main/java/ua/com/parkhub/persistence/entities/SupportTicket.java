package ua.com.parkhub.persistence.entities;

import javax.persistence.Entity;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "support_ticket", schema = "park_hub")
public class SupportTicket implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @NotNull
    private String description;

    @Column
    @NotNull
    private boolean isSolved = false;

    @ManyToOne
    @JoinColumn(name = "ticket_type_id")
    private SupportTicketTypeModel supportTicketType;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private Customer customer;

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @ManyToMany
    @JoinTable(name = "ticket_solver",
               joinColumns = { @JoinColumn(name = "ticket_id") },
               inverseJoinColumns = { @JoinColumn(name = "solver_id") }
    )
    private List<User> solvers;

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

    public List<User> getSolvers() {
        return solvers;
    }

    public void setSolvers(List<User> solvers) {
        this.solvers = solvers;
    }
}
