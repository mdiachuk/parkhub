package ua.com.parkhub.validation.validators;

import ua.com.parkhub.validation.annotations.ValidPassword;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PasswordValidator implements ConstraintValidator<ValidPassword, String> {

    private static final String PATTERN  = "(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{6,}";

    @Override
    public void initialize(ValidPassword constraintAnnotation) {
    }

    @Override
    public boolean isValid(String password, ConstraintValidatorContext context) {
        return (validatePassword(password));
    }

    private boolean validatePassword(String password) {
        Pattern pattern = Pattern.compile(PATTERN);
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }
}
