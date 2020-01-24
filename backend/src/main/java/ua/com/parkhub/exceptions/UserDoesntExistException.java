package ua.com.parkhub.exceptions;

public class UserDoesntExistException extends ParkHubException{
    public UserDoesntExistException(String message) {
        super(message);
    }
    private StatusCode statusCode;

    public UserDoesntExistException(StatusCode statusCode){
        this.statusCode = statusCode;
    }

    public int getStatusCode() {
        return statusCode.getCode();
    }
}
