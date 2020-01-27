package ua.com.parkhub.exceptions;

public class ExistingParkingException extends RuntimeException {
    public ExistingParkingException(String message) {
        super(message);
    }
}
