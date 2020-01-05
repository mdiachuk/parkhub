package ua.com.parkhub.dto;

import java.util.Objects;

public class SupportTicketTypeDTO {

    private Long id;

    private String type;

    private boolean isActive = true;

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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SupportTicketTypeDTO that = (SupportTicketTypeDTO) o;
        return isActive == that.isActive &&
                Objects.equals(id, that.id) &&
                Objects.equals(type, that.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, type, isActive);
    }

    @Override
    public String toString() {
        return "SupportTicketTypeDTO{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", isActive=" + isActive +
                '}';
    }
}
