package ua.com.parkhub.exceptions;

public class SlotNotFoundException extends ParkHubException {
    private Long slotId;

    private SlotNotFoundException(Long slotId) {
        this.slotId = slotId;
    }

    public String getMessage() {
        StringBuilder sb = new StringBuilder();
        sb.append("Parking with id : ").append(slotId).append(" not found");
        return sb.toString();
    }
}
