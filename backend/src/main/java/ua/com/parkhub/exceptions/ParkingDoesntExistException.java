package ua.com.parkhub.exceptions;

public class ParkingDoesntExistException extends RuntimeException {

    private final StatusCode statusCode;

    public ParkingDoesntExistException(String message, StatusCode statusCode) {
        super(message);
        this.statusCode = statusCode;
    }

    public ParkingDoesntExistException(StatusCode statusCode){
        this.statusCode = statusCode;
    }

    public StatusCode getStatusCode() {
        return statusCode;
    }
}
