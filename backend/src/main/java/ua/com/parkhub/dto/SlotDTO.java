package ua.com.parkhub.dto;

import java.io.Serializable;

public class SlotDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    private Long id;
    private String slotNumber;
    //TODO
    /*private boolean isReserved;
    private boolean isActive;*/

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSlotNumber() {
        return slotNumber;
    }

    public void setSlotNumber(String slotNumber) {
        this.slotNumber = slotNumber;
    }

    @Override
    public String toString() {
        return "SlotDTO{" +
                "id=" + id +
                ", slotNumber='" + slotNumber + '\'' +
                '}';
    }
}
