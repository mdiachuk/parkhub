package ua.com.parkhub.dto;

public class AdminSupportTicketDTO {
    private Long id;
    private String description;
    private boolean isSolved;
    private String supportTicketType;

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

    public String getSupportTicketType() {
        return supportTicketType;
    }

    public void setSupportTicketType(String supportTicketType) {
        this.supportTicketType = supportTicketType;
    }
}
