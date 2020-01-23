package ua.com.parkhub.exceptions;

public enum StatusCode {

    ACCOUNT_BLOCKED (1),
    CANNOT_ACTIVATE(2),
    NO_ACCOUNT_FOUND(4),
    INVALID_CREDENTIALS(8),
    NO_BLOCKED_USER(16),
    CUSTOMER_NOT_FOUND(32),
    BOOKING_NOT_FOUND(64),
    PARKING_DOESNT_EXIST(128),
    ROLE_PENDING(256),
    USER_NOT_FOUND(512);

    private int code;

    StatusCode(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}

