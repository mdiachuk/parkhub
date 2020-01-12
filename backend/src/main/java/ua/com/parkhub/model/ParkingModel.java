package ua.com.parkhub.model;


import java.util.List;

public class ParkingModel {

    private Long id;
    private String parkingName;
    private Integer slotsNumber;
    private Integer tariff;
    private AddressModel addressModel;
    private List<SlotModel> slots;
    private boolean isActive;
    private UserModel owner;
    private ParkingInfoModel info;

    public void setId(Long id) {
        this.id = id;
    }

    public void setSlotsNumber(Integer slotsNumber) {
        this.slotsNumber = slotsNumber;
    }

    public void setTariff(Integer tariff) {
        this.tariff = tariff;
    }

    public Integer getTariff() {
        return tariff;
    }

    public List<SlotModel> getSlots() {
        return slots;
    }

    public void setSlots(List<SlotModel> slots) {
        this.slots = slots;
    }

    public boolean getActive() {
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


    public boolean isActive() {
        return isActive;
    }


    public Long getId() {
        return id;
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

    public ParkingInfoModel getInfo() {
        return info;
    }

    public void setInfo(ParkingInfoModel info) {
        this.info = info;
    }

    public AddressModel getAddressModel() {
        return addressModel;
    }

    public void setAddressModel(AddressModel addressModel) {
        this.addressModel = addressModel;
    }
}
