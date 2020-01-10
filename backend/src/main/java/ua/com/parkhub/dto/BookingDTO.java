package ua.com.parkhub.dto;

import ua.com.parkhub.util.formatter.DateFormatter;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

public class BookingDTO {

    @NotNull
    private String checkIn;

    @NotNull
    private String checkOut;

    @NotNull
    private String slot;


    public String getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(LocalDateTime checkIn) {
        this.checkIn = DateFormatter.convertDateTimeToString(checkIn);
    }

    public String getCheckOut() {
        return checkOut;
    }

    public void setCheckOut(LocalDateTime checkOut) {
        this.checkOut = DateFormatter.convertDateTimeToString(checkOut);
    }

    public String getSlot() {
        return slot;
    }

    public void setSlot(SlotDTO slot) {
        this.slot = slot.getSlotNumber();
    }

    @Override
    public String toString() {
        return "BookingDTO{" +
                "checkIn='" + checkIn + '\'' +
                ", checkOut='" + checkOut + '\'' +
                ", slot='" + slot + '\'' +
                '}';
    }
}
