package ua.com.parkhub.model;

import java.util.Set;

public class SupportTicketModel {

    private Long id;
    private String description;
    private boolean isSolved;
    private SupportTicketTypeModel supportTicketType;

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
}
