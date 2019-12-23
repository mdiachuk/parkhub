package ua.com.parkhub.validation.validators;

import ua.com.parkhub.dto.ManagerRegistrationDataDTO;
import ua.com.parkhub.validation.annotations.PasswordMatches;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordMatchesValidator
        implements ConstraintValidator<PasswordMatches, Object> {

    @Override
    public void initialize(PasswordMatches constraintAnnotation) {
    }

    @Override
    public boolean isValid(Object obj, ConstraintValidatorContext context) {
        ManagerRegistrationDataDTO user = (ManagerRegistrationDataDTO) obj;
        return user.getPassword().equals(user.getMatchingPassword());
    }
}
