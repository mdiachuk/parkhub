package ua.com.parkhub.exceptions;

public class PaymentException extends ParkHubException {

    private StatusCode statusCode;

    public PaymentException(){super();}

    public PaymentException(String message) {
        super(message);
    }

    public PaymentException(StatusCode statusCode){
        this.statusCode = statusCode;
    }

    public int getStatusCode() {
        return statusCode.getCode();
    }
}
