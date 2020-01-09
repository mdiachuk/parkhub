package ua.com.parkhub.model;

import java.util.Set;

public class ParkingModel {

    private long id;
    private String parkingName;
    private int slotsNumber;
    private int tariff;
    private AddressModel addressModel;
    private Set<SlotModel> slots;
    private boolean isActive;
    private UserModel owner;

    public Set<SlotModel> getSlots() {
        return slots;
    }

    public UserModel getOwner() {
        return owner;
    }

    public void setOwner(UserModel owner) {
        this.owner = owner;
    }

    public void setSlots(Set<SlotModel> slots) {
        this.slots = slots;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getParkingName() {
        return parkingName;
    }

    public void setParkingName(String parkingName) {
        this.parkingName = parkingName;
    }

    public int getSlotsNumber() {
        return slotsNumber;
    }

    public void setSlotsNumber(int slotsNumber) {
        this.slotsNumber = slotsNumber;
    }

    public int getTariff() {
        return tariff;
    }

    public void setTariff(int tariff) {
        this.tariff = tariff;
    }

    public AddressModel getAddressModel() {
        return addressModel;
    }

    public void setAddressModel(AddressModel addressModel) {
        this.addressModel = addressModel;
    }


    //SOME BUSINESS LOGIC
}
