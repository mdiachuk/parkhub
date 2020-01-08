package ua.com.parkhub.exceptions;

public enum StatusCode {

    ACCOUNT_BLOCKED (1),
    CANNOT_ACTIVATE(2),
    NO_ACCOUNT_FOUND(4),
    INVALID_CREDENTIALS(8),
    PARKING_DOESNT_EXIST(16);

    private int code;

    StatusCode(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}

