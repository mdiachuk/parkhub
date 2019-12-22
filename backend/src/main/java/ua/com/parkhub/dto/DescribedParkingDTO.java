package ua.com.parkhub.dto;

public class DescribedParkingDTO{

    private String parkingName;
    private String parkingAddress;
    private int slotsNumber;
    private int tariff;

    public DescribedParkingDTO(String parkingName, String parkingAddress, int slotsNumber, int tariff) {
        this.parkingName = parkingName;
        this.parkingAddress = parkingAddress;
        this.slotsNumber = slotsNumber;
        this.tariff = tariff;
    }

    public String getParkingName() {
        return parkingName;
    }

    public void setParkingName(String parkingName) {
        this.parkingName = parkingName;
    }

    public String getParkingAddress() {
        return parkingAddress;
    }

    public void setParkingAddress(String parkingAddress) {
        this.parkingAddress = parkingAddress;
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
}
