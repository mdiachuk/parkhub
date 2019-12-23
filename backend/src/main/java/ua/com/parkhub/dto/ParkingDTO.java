package ua.com.parkhub.dto;


public class ParkingDTO {
    private long id;
    private String parkingName;
    private String address;
    private int slotNumber;
    private int tariff;
    private String fullness;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getParkingName() {
        return parkingName;
    }

    public String getAddress() {
        return address;
    }

    public int getSlotNumber() {
        return slotNumber;
    }

    public void setSlotNumber(int slotNumber) {
        this.slotNumber = slotNumber;
    }

    public int getTariff() {
        return tariff;
    }

    public String getFullness() {
        return fullness;
    }

    public void setParkingName(String parkingName) {
        this.parkingName = parkingName;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setTariff(int tariff) {
        this.tariff = tariff;
    }

    public void setFullness(String fullness) {
        this.fullness = fullness;
    }
}
