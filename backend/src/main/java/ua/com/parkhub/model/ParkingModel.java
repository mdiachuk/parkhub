package ua.com.parkhub.model;

import ua.com.parkhub.persistence.entities.Slot;
import ua.com.parkhub.persistence.entities.User;

import java.util.Set;

public class ParkingModel {

    private String parkingName;
    private int slotsNumber;
    private int tariff;
    private boolean isActive = true;
    private User owner;
    AddressModel addressModel;

    public ParkingModel(String parkingName, int slotsNumber, int tariff, boolean isActive, User owner, AddressModel addressModel) {
        this.parkingName = parkingName;
        this.slotsNumber = slotsNumber;
        this.tariff = tariff;
        this.isActive = isActive;
        this.owner = owner;
        this.addressModel = addressModel;
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

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public AddressModel getAddressModel() {
        return addressModel;
    }

    public void setAddressModel(AddressModel addressModel) {
        this.addressModel = addressModel;
    }


    //SOME BUSINESS LOGIC
}
