package ua.com.parkhub.exceptions;

public class CustomerException extends ParkHubException {

    private StatusCode statusCode;

    public CustomerException(String message) {
        super(message);
    }

    public CustomerException(){ super();}

    public CustomerException(StatusCode statusCode){
        this.statusCode = statusCode;
    }

    public int getStatusCode() {
        return statusCode.getCode();
    }
}
