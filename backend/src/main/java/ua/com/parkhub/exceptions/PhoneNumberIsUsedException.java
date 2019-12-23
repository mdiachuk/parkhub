package ua.com.parkhub.exceptions;

public class PhoneNumberIsUsedException extends RuntimeException {

    public PhoneNumberIsUsedException() {
        super("Account with this phone number already exists!");
    }
}
