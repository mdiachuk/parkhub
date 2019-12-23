package ua.com.parkhub.model;

import ua.com.parkhub.persistence.entities.ParkingEntity;

import java.util.Objects;

public class Slot extends AbstractModel {

    private Long id;
    private String slotNumber;
    private boolean isReserved;
    private boolean isActive;

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

    public boolean isReserved() {
        return isReserved;
    }

    public void setReserved(boolean reserved) {
        isReserved = reserved;
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
        if (o == null || getClass() != o.getClass()) return false;
        Slot slot = (Slot) o;
        return Objects.equals(id, slot.id) &&
                Objects.equals(slotNumber, slot.slotNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, slotNumber);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Slot" + ", id: ").append(id);
        sb.append(", slotNumber: ").append(slotNumber);
        sb.append(", isReserved: ").append(isReserved);
        sb.append(", isActive: ").append(isActive);
        return sb.toString();
    }
}
