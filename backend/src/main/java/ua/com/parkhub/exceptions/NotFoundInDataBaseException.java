package ua.com.parkhub.exceptions;

public class NotFoundInDataBaseException extends RuntimeException {

    public NotFoundInDataBaseException(String message) {
        super(message);
    }
}
