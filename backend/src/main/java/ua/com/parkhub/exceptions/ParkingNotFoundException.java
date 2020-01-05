package ua.com.parkhub.exceptions;

public class ParkingNotFoundException extends ParkHubException {
    private Long parkingId;

    public static ParkingNotFoundException createWith(Long parkingId) {
        return new ParkingNotFoundException(parkingId);
    }

    private ParkingNotFoundException(Long parkingId) {
        this.parkingId = parkingId;
    }

    public String getMessage() {
        StringBuilder sb = new StringBuilder();
        sb.append("Parking with id : ").append(parkingId).append(" not found");
        return sb.toString();
    }


}
