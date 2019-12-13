package ua.com.parkhub.persistence.entities;

import javax.persistence.Entity;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "support_ticket", schema = "park_hub")
public class SupportTicket implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "support_ticket_generator")
    @SequenceGenerator(name = "support_ticket_generator", sequenceName = "park_hub.support_ticket_id_seq", allocationSize = 1)
    private Long id;

    @Column
    @NotNull
    private String description;

    @Column
    @NotNull
    private boolean isSolved = false;

    @ManyToOne
    @JoinColumn(name = "ticket_type_id")
    private SupportTicketType supportTicketType;

    @ManyToMany
    @JoinTable(name = "ticket_solver",
               joinColumns = { @JoinColumn(name = "ticket_id") },
               inverseJoinColumns = { @JoinColumn(name = "solver_id") }
    )
    private Set<User> solvers;

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

    public SupportTicketType getSupportTicketType() {
        return supportTicketType;
    }

    public void setSupportTicketType(SupportTicketType supportTicketType) {
        this.supportTicketType = supportTicketType;
    }

    public Set<User> getSolvers() {
        return solvers;
    }

    public void setSolvers(Set<User> solvers) {
        this.solvers = solvers;
    }
}
