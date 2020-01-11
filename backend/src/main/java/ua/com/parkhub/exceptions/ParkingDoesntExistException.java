package ua.com.parkhub.exceptions;

public class ParkingDoesntExistException extends RuntimeException {

    public ParkingDoesntExistException(String message) {
        super(message);
    }

    public ParkingDoesntExistException(StatusCode statusCode){
        this.statusCode = statusCode;
    }

    private StatusCode statusCode;

    public StatusCode getStatusCode() {
        return statusCode;
    }
}
