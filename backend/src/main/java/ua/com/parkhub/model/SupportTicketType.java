package ua.com.parkhub.model;

import java.util.Objects;

public class SupportTicketType extends AbstractModel {

    private Long id;
    private String type;
    private boolean isActive;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SupportTicketType)) return false;

        SupportTicketType that = (SupportTicketType) o;

        if (isActive != that.isActive) return false;
        if (!Objects.equals(id, that.id)) return false;
        return Objects.equals(type, that.type);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (isActive ? 1 : 0);
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("SupportTicketType" + ", id: ").append(id);
        sb.append(", type: ").append(type);
        sb.append(", isActive: ").append(isActive);
        return sb.toString();
    }
}
