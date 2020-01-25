package ua.com.parkhub.validation.validators;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.validation.ConstraintValidatorContext;
import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

class EmailValidatorTest {

    private static final int TIMEOUT = 2000;
    private static ConstraintValidatorContext context;
    private static EmailValidator validator;

    @BeforeAll
    private static void init() {
        context = Mockito.mock(ConstraintValidatorContext.class);
        validator = new EmailValidator();
    }

    @Test
    void test_isValid_correctEmail_1() {
        helper_isValid_correctEmail("aaaaa@gmail.com");
    }

    @Test
    void test_isValid_correctEmail_2() {
        helper_isValid_correctEmail("bb@ukr.net");
    }

    @Test
    void test_isValid_correctEmail_3() {
        helper_isValid_correctEmail("cccc@yahoo.com");
    }

    @Test
    void test_isValid_incorrectEmail_1() {
        helper_isValid_incorrectEmail("aaaaagmail.com");
    }

    @Test
    void test_isValid_incorrectEmail_2() {
        helper_isValid_incorrectEmail("bb@ukrnet");
    }

    @Test
    void test_isValid_incorrectEmail_3() {
        helper_isValid_incorrectEmail("cccc@yahoo");
    }

    private void helper_isValid_correctEmail(String email) {
        assertTimeout(Duration.ofMillis(TIMEOUT), () -> assertTrue(validator.isValid(email, context)));
    }

    private void helper_isValid_incorrectEmail(String email) {
        assertTimeout(Duration.ofMillis(TIMEOUT), () -> assertFalse(validator.isValid(email, context)));
    }
}