package ua.com.parkhub.exceptions;

public enum StatusCode {

    CUSTOMER_NOT_FOUND(16),
    BOOKING_NOT_FOUND(32);

    private int code;

    StatusCode(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
