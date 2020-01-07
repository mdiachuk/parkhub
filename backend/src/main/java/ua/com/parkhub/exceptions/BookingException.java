package ua.com.parkhub.exceptions;

public class BookingException extends ParkHubException {

    private StatusCode statusCode;

    public BookingException(){ super();}

    public BookingException(String message) {
        super(message);
    }

    public BookingException(StatusCode statusCode){
        this.statusCode = statusCode;
    }

    public int getStatusCode() {
        return statusCode.getCode();
    }
}
