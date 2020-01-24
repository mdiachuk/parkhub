package ua.com.parkhub.model;


import java.util.List;
import java.util.Objects;

public class ParkingModel {

    private ParkingInfoModel info;
    private List<SlotModel> slots;

    public ParkingInfoModel getInfo() {
        return info;
    }

    public void setInfo(ParkingInfoModel info) {
        this.info = info;
    }

    public List<SlotModel> getSlots() {
        return slots;
    }

    public void setSlots(List<SlotModel> slots) {
        this.slots = slots;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ParkingModel that = (ParkingModel) o;
        return Objects.equals(info, that.info) &&
                Objects.equals(slots, that.slots);
    }

    @Override
    public int hashCode() {
        return Objects.hash(info, slots);
    }

    @Override
    public String toString() {
        return "ParkingModel{" +
                "id=" + info.getId() +
                ", parkingName='" + info.getParkingName()+ '\'' +
                ", slotsNumber=" + info.getSlotsNumber() +
                ", addressModel=" + info.getAddressModel() +
                ", slots=" + slots +
                ", isActive=" + info.isActive() +
                ", owner=" +info.getOwner() +
                '}';
    }
}
