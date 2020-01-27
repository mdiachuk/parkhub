package ua.com.parkhub.dto;

public class AdminSupportTicketDTO {

    private long id;
    private String description;
    private String ticketHighlight;
    private boolean isSolved;
    private String supportTicketType;
    private long targetManagerId;

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

    public String getTicketHighlight() {
        return ticketHighlight;
    }

    public void setTicketHighlight(String ticketHighlight) {
        this.ticketHighlight = ticketHighlight;
    }

    public long getTargetManagerId() {
        return targetManagerId;
    }

    public void setTargetManagerId(long targetManagerId) {
        this.targetManagerId = targetManagerId;
    }
}
