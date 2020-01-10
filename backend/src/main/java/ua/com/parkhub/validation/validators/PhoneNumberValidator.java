package ua.com.parkhub.validation.validators;

import ua.com.parkhub.validation.annotations.ValidPhoneNumber;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PhoneNumberValidator implements ConstraintValidator<ValidPhoneNumber, String> {

    private Pattern pattern;
    private Matcher matcher;
    private static final String PHONE_NUMBER_PATTERN = "^\\+?3?8?(0\\d{9})$";

    @Override
    public void initialize(ValidPhoneNumber constraintAnnotation) {
    }

    @Override
    public boolean isValid(String phoneNumber, ConstraintValidatorContext context) {
        return (validatePhoneNumber(phoneNumber));
    }

    private boolean validatePhoneNumber(String phoneNumber) {
        pattern = Pattern.compile(PHONE_NUMBER_PATTERN);
        matcher = pattern.matcher(phoneNumber);
        return matcher.matches();
    }
}
