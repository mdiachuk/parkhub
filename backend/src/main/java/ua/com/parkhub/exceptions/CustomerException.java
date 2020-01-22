package ua.com.parkhub.exceptions;

public class CustomerException extends RuntimeException {

    private StatusCode statusCode;

    public CustomerException(StatusCode statusCode){
        this.statusCode = statusCode;
    }

    public int getStatusCode() {
        return statusCode.getCode();
    }
}
