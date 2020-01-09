package ua.com.parkhub.dto;

import ua.com.parkhub.util.formatter.DateFormatter;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;

public class BookingDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    @NotNull
    private String checkIn;
    @NotNull
    private String slot;

    public String getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(LocalDateTime checkIn) {
        this.checkIn = DateFormatter.format(checkIn);
    }

    public String getSlot() {
        return slot;
    }

  /*  public void setSlot(Slot slot) {
        this.slot = slot.getSlotNumber();
    }*/

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("BookingDTO" + ", checkIn: ").append(checkIn);
        sb.append(", slotNumber: ").append(slot);
        return sb.toString();
    }
}
