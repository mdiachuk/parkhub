package ua.com.parkhub.model;


import java.util.Objects;
import java.util.Set;

public class ParkingModel {

    private Long id;
    private String parkingName;
    private int slotsNumber;
    private int tariff;
    private AddressModel addressModel;
    private Set<SlotModel> slots;
    private boolean isActive;
    private UserModel owner;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public Set<SlotModel> getSlots() {
        return slots;
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

    public UserModel getOwner() {
        return owner;
    }

    public void setOwner(UserModel owner) {
        this.owner = owner;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ParkingModel that = (ParkingModel) o;
        return slotsNumber == that.slotsNumber &&
                tariff == that.tariff &&
                isActive == that.isActive &&
                Objects.equals(id, that.id) &&
                Objects.equals(parkingName, that.parkingName) &&
                Objects.equals(addressModel, that.addressModel) &&
                Objects.equals(slots, that.slots) &&
                Objects.equals(owner, that.owner);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, parkingName, slotsNumber, tariff, addressModel, slots, isActive, owner);
    }

    @Override
    public String toString() {
        return "ParkingModel{" +
                "id=" + id +
                ", parkingName='" + parkingName + '\'' +
                ", slotsNumber=" + slotsNumber +
                ", tariff=" + tariff +
                ", addressModel=" + addressModel +
                ", slots=" + slots +
                ", isActive=" + isActive +
                ", owner=" + owner +
                '}';
    }

    //SOME BUSINESS LOGIC
}
