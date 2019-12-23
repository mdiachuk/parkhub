package ua.com.parkhub.model;

public class ParkingModel {
    private long id;

    private String parkingName;

    private int slotsNumber;

    private int tariff;

    private AddressModel addressModel;

    public ParkingModel(long id, String parkingName, int slotsNumber, int tariff, AddressModel addressModel) {
        this.id = id;
        this.parkingName = parkingName;
        this.slotsNumber = slotsNumber;
        this.tariff = tariff;
        this.addressModel = addressModel;
    }

    public ParkingModel() {
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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    //SOME BUSINESS LOGIC
}
