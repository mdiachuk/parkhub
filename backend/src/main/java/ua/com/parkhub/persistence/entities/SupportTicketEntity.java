package ua.com.parkhub.persistence.entities;

import javax.persistence.Entity;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "support_ticket", schema = "park_hub")
public class SupportTicketEntity implements Serializable {

    private static final long serialVersionUID = 1L;

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
    private SupportTicketTypeEntity supportTicketType;

    @ManyToMany
    @JoinTable(name = "ticket_solver",
            joinColumns = {@JoinColumn(name = "ticket_id")},
            inverseJoinColumns = {@JoinColumn(name = "solver_id")}
    )
    private List<UserEntity> solvers;

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

    public SupportTicketTypeEntity getSupportTicketType() {
        return supportTicketType;
    }

    public void setSupportTicketType(SupportTicketTypeEntity supportTicketType) {
        this.supportTicketType = supportTicketType;
    }

    public List<UserEntity> getSolvers() {
        return solvers;
    }

    public void setSolvers(List<UserEntity> solvers) {
        this.solvers = solvers;
    }
}
