package ua.com.parkhub.model;


import java.util.List;

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

//    @Override
//    public String toString() {
//        return "ParkingModel{" +
//                "id=" + info.getId() +
//                ", parkingName='" + info.getParkingName()+ '\'' +
//                ", slotsNumber=" + info.getSlotsNumber() +
//                ", tariff=" + info.getTariff() +
//                ", addressModel=" + info.getAddressModel() +
//                ", slots=" + slots +
//                ", isActive=" + info.isActive() +
//                ", owner=" +info.getOwner() +
//                '}';
//    }
}
