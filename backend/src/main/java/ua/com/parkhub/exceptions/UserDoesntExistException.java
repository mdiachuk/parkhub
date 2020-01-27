package ua.com.parkhub.exceptions;

public class UserDoesntExistException extends ParkHubException{
    private StatusCode statusCode;

    public UserDoesntExistException(String message, StatusCode statusCode){
        super(message);
        this.statusCode = statusCode;
    }

}
