package ua.com.parkhub.model;

public class ParkingModel {

    private UserModel parkingOwner;
    private String parkingName;
    private AddressModel address;
    private int slotsNumber;
    private int tariff;
    private boolean isActive;

    public UserModel getUser() {
        return parkingOwner;
    }

    public void setUser(UserModel parkingOwner) {
        this.parkingOwner = parkingOwner;
    }

    public String getParkingName() {
        return parkingName;
    }

    public void setParkingName(String parkingName) {
        this.parkingName = parkingName;
    }

    public AddressModel getAddress() {
        return address;
    }

    public void setAddress(AddressModel address) {
        this.address = address;
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
}
