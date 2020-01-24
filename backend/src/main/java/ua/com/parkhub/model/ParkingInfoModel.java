package ua.com.parkhub.model;

import java.util.Objects;

public class ParkingInfoModel {

    private Long id;
    private String parkingName;
    private Integer slotsNumber;
    private Integer tariff;
    private AddressModel addressModel;
    private Boolean isActive;
    private UserModel owner;

    public UserModel getOwner() {
        return owner;
    }

    public void setOwner(UserModel owner) {
        this.owner = owner;
    }

    public Boolean isActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ParkingInfoModel that = (ParkingInfoModel) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(parkingName, that.parkingName) &&
                Objects.equals(slotsNumber, that.slotsNumber) &&
                Objects.equals(tariff, that.tariff) &&
                Objects.equals(addressModel, that.addressModel) &&
                Objects.equals(isActive, that.isActive) &&
                Objects.equals(owner, that.owner);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, parkingName, slotsNumber, tariff, addressModel, isActive, owner);
    }
}
