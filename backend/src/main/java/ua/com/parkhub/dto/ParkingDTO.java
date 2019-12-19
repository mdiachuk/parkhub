package ua.com.parkhub.dto;

public class ParkingDTO {

    private String parkingName;
    private String parkingAddress;

    public ParkingDTO(String parkingName, String parkingAddress) {
        this.parkingName = parkingName;
        this.parkingAddress = parkingAddress;
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
