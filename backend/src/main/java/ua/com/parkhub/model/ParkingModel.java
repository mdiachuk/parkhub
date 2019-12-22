package ua.com.parkhub.model;

public class ParkingModel {

    private String parkingName;

    private int slotsNumber;

    private int tariff;

    private AddressModel address;

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

    public AddressModel getAddress() {
        return address;
    }

    public void setAddress(AddressModel address) {
        this.address = address;
    }
}
