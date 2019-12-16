package ua.com.parkhub.exceptions;

public class ParkHubException extends RuntimeException {

    public ParkHubException() {
        super("Сталася халепа");
    }

    public ParkHubException(String message) {
        super(message);
    }

    public ParkHubException(String message, Throwable cause) {
        super(message, cause);
    }

    public ParkHubException(Throwable cause) {
        super(cause);
    }
}
