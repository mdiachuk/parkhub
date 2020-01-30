package ua.com.parkhub.validation.validators;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.validation.ConstraintValidatorContext;
import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

class PhoneNumberValidatorTest {

    private static final int TIMEOUT = 2000;
    private static ConstraintValidatorContext context;
    private static PhoneNumberValidator validator;

    @BeforeAll
    private static void init() {
        context = Mockito.mock(ConstraintValidatorContext.class);
        validator = new PhoneNumberValidator();
    }

    @Test
    void test_isValid_correctPhoneNumber_1() {
        helper_isValid_correctPhoneNumber("380639945557");
    }

    @Test
    void test_isValid_correctPhoneNumber_2() {
        helper_isValid_correctPhoneNumber("380679951225");
    }

    @Test
    void test_isValid_correctPhoneNumber_3() {
        helper_isValid_correctPhoneNumber("380332467890");
    }

    @Test
    void test_isValid_incorrectPhoneNumber_1() {
        helper_isValid_incorrectPhoneNumber("245947");
    }

    @Test
    void test_isValid_incorrectPhoneNumber_2() {
        helper_isValid_incorrectPhoneNumber("ppppPP");
    }

    @Test
    void test_isValid_incorrectPhoneNumber_3() {
        helper_isValid_incorrectPhoneNumber("470639956668");
    }

    private void helper_isValid_correctPhoneNumber(String password) {
        assertTimeout(Duration.ofMillis(TIMEOUT), () -> assertTrue(validator.isValid(password, context)));
    }

    private void helper_isValid_incorrectPhoneNumber(String password) {
        assertTimeout(Duration.ofMillis(TIMEOUT), () -> assertFalse(validator.isValid(password, context)));
    }
}