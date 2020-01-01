package ua.com.parkhub.exceptions;

public class PermissionException extends RuntimeException{

    public PermissionException() {
        super();
    }

    public PermissionException(String message) {
        super(message);
    }
}
