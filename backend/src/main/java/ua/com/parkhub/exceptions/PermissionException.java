package ua.com.parkhub.exceptions;

/**
 * Created by Miha on 17.11.2017.
 */
public class PermissionException extends RuntimeException{

    public PermissionException() {
        super();
    }

    public PermissionException(String message) {
        super(message);
    }
}
