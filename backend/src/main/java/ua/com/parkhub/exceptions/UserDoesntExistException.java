package ua.com.parkhub.exceptions;

public class UserDoesntExistException extends ParkHubException{
    public UserDoesntExistException(String message) {
        super(message);
    }

    public UserDoesntExistException(StatusCode statusCode){
        this.statusCode = statusCode;
    }

    private StatusCode statusCode;

    public StatusCode getStatusCode() {
        return statusCode;
    }
}
