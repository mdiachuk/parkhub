package ua.com.parkhub.exceptions;

import org.springframework.validation.ObjectError;

import java.util.List;

public class AddBookingException extends ParkHubException {
    List<ObjectError> errors;

    public static AddBookingException createWith(List<ObjectError> errors) {
        return new AddBookingException(errors);
    }

    private AddBookingException(List<ObjectError> errors) {
        this.errors = errors;
    }

    public List<ObjectError> getErrors() {
        return errors;
    }
}
