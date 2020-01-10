package ua.com.parkhub.validation.validators;

import ua.com.parkhub.validation.annotations.ValidUsreouCode;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UsreouCodeValidator implements ConstraintValidator<ValidUsreouCode, String> {

    @Override
    public void initialize(ValidUsreouCode constraintAnnotation) {
    }

    @Override
    public boolean isValid(String usreouCode, ConstraintValidatorContext context) {
        return (validatePhoneNumber(usreouCode));
    }

    private boolean validatePhoneNumber(String usreouCode) {
        if (usreouCode.length() != 8) {
            return false;
        }
        try {
            Integer.parseInt(usreouCode);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
