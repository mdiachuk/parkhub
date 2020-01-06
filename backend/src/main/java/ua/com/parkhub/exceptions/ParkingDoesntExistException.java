package ua.com.parkhub.exceptions;

public class ParkingDoesntExistException extends RuntimeException {

    public ParkingDoesntExistException(String message) {
        super(message);
    }

}
