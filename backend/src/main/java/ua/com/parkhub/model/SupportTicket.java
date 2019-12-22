package ua.com.parkhub.model;

import ua.com.parkhub.persistence.entities.SupportTicketTypeEntity;
import ua.com.parkhub.persistence.entities.UserEntity;

import java.util.List;
import java.util.Objects;

public class SupportTicket extends AbstractModel {

    private Long id;
    private String description;
    private boolean isSolved;
    private SupportTicketType supportTicketType;
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

    public SupportTicketType getSupportTicketType() {
        return supportTicketType;
    }

    public void setSupportTicketType(SupportTicketType supportTicketType) {
        this.supportTicketType = supportTicketType;
    }

    public List<User> getSolvers() {
        return solvers;
    }

    public void setSolvers(List<User> solvers) {
        this.solvers = solvers;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SupportTicket)) return false;

        SupportTicket that = (SupportTicket) o;

        if (isSolved != that.isSolved) return false;
        if (!Objects.equals(id, that.id)) return false;
        if (!Objects.equals(description, that.description)) return false;
        if (!Objects.equals(supportTicketType, that.supportTicketType))
            return false;
        return Objects.equals(solvers, that.solvers);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (isSolved ? 1 : 0);
        result = 31 * result + (supportTicketType != null ? supportTicketType.hashCode() : 0);
        result = 31 * result + (solvers != null ? solvers.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("SupportTicket" + ", id: ").append(id);
        sb.append(", description: ").append(description);
        sb.append(", isSolved: ").append(isSolved);
        sb.append(", isSolved: ").append(supportTicketType);
        sb.append(", isSolved: ").append(solvers);
        return sb.toString();
    }
}
