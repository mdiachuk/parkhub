package ua.com.parkhub.model;

public class SlotModel {

    private ParkingModel parking;
    private String slotNumber;
    private boolean isReserved;
    private boolean isActive;

    public SlotModel(ParkingModel parking, String slotNumber, boolean isReserved, boolean isActive) {
        this.parking = parking;
        this.slotNumber = slotNumber;
        this.isReserved = isReserved;
        this.isActive = isActive;
    }

    public ParkingModel getParking() {
        return parking;
    }

    public void setParking(ParkingModel parking) {
        this.parking = parking;
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
}
