package ua.com.parkhub.exceptions;

public class PermissionException extends ParkHubException{

    private StatusCode statusCode;

    public PermissionException() {
        super();
    }

    public PermissionException(String message) {
        super(message);
    }

    public PermissionException(StatusCode statusCode) {
        this.statusCode = statusCode;
    }

    public int getStatusCode() {
        return statusCode.getCode();
    }
}
