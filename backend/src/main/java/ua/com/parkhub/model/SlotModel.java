package ua.com.parkhub.model;


import java.util.Objects;

public class SlotModel {

    private Long id;
    private String slotNumber;
    private boolean isReserved = false;
    private boolean isActive = true;

    public Long getId() {
        return id;
    }

    public void setId(long id) {
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SlotModel slotModel = (SlotModel) o;
        return isReserved == slotModel.isReserved &&
                isActive == slotModel.isActive &&
                Objects.equals(id, slotModel.id) &&
                Objects.equals(slotNumber, slotModel.slotNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, slotNumber, isReserved, isActive);
    }

    @Override
    public String toString() {
        return "SlotModel{" +
                "id=" + id +
                ", slotNumber='" + slotNumber + '\'' +
                ", isReserved=" + isReserved +
                ", isActive=" + isActive +
                '}';
    }
}