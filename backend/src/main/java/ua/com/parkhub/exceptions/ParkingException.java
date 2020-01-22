package ua.com.parkhub.exceptions;

public class ParkingException extends RuntimeException {
    private Long parkingId;

    public static ParkingException createWith(Long parkingId) {
        return new ParkingException(parkingId);
    }

    private ParkingException(Long parkingId) {
        this.parkingId = parkingId;
    }

    public String getMessage() {
        StringBuilder sb = new StringBuilder();
        sb.append("Parking with id : ").append(parkingId).append(" not found");
        return sb.toString();
    }


}
