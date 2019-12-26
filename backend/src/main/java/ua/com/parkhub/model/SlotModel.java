package ua.com.parkhub.model;


public class SlotModel {

    private String slotNumber;
    private boolean isReserved = false;
    private boolean isActive = true;
    private ParkingModel parking;

    public String getSlotNumber() {
        return slotNumber;
    }

    public ParkingModel getParking() {
        return parking;
    }

    public void setParking(ParkingModel parking) {
        this.parking = parking;
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
