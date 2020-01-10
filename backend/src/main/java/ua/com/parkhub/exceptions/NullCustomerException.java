package ua.com.parkhub.exceptions;

public class NullCustomerException extends ParkHubException {

    public NullCustomerException() {
        super("Customer is not found");
    }

    public NullCustomerException(String message) {
        super(message);
    }

//    public NullCustomerException(String message, Throwable cause) {
//        super(message, cause);
//    }
//
//    public NullCustomerException(Throwable cause) {
//        super(cause);
//    }
}
