package ua.com.parkhub.dto;

public class ShortParkingDTO {

    private long id;
    private String parkingName;
    private String parkingAddress;

    public ShortParkingDTO(long id, String parkingName, String parkingAddress) {
        this.id = id;
        this.parkingName = parkingName;
        this.parkingAddress = parkingAddress;
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

    public String getParkingAddress() {
        return parkingAddress;
    }

    public void setParkingAddress(String parkingAddress) {
        this.parkingAddress = parkingAddress;
    }
}
