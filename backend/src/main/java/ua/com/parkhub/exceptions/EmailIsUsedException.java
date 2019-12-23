package ua.com.parkhub.exceptions;

public class EmailIsUsedException extends RuntimeException {

    public EmailIsUsedException() {
        super("Account with this email already exists!");
    }
}
