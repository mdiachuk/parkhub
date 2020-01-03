package ua.com.parkhub.model;

import ua.com.parkhub.persistence.entities.SupportTicketTypeModel;

import java.util.List;
import java.util.Objects;

public class SupportTicket extends AbstractModel {

    private Long id;
    private String description;
    private boolean isSolved;
    private SupportTicketTypeModel supportTicketType;
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

    public SupportTicketTypeModel getSupportTicketType() {
        return supportTicketType;
    }

    public void setSupportTicketType(SupportTicketTypeModel supportTicketType) {
        this.supportTicketType = supportTicketType;
    }

    public List<UserModel> getSolvers() {
        return solvers;
    }

    public void setSolvers(List<UserModel> solvers) {
        this.solvers = solvers;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SupportTicket that = (SupportTicket) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(description, that.description) &&
                Objects.equals(supportTicketType, that.supportTicketType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, description, supportTicketType);
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
