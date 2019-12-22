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
        if (!(o instanceof Slot)) return false;

        Slot slot = (Slot) o;

        if (isReserved != slot.isReserved) return false;
        if (isActive != slot.isActive) return false;
        if (!Objects.equals(id, slot.id)) return false;
        return Objects.equals(slotNumber, slot.slotNumber);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (slotNumber != null ? slotNumber.hashCode() : 0);
        result = 31 * result + (isReserved ? 1 : 0);
        result = 31 * result + (isActive ? 1 : 0);
        return result;
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
