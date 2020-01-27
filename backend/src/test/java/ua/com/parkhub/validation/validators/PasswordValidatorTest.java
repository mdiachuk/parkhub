package ua.com.parkhub.validation.validators;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.validation.ConstraintValidatorContext;
import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

class PasswordValidatorTest {

    private static final int TIMEOUT = 2000;
    private static ConstraintValidatorContext context;
    private static PasswordValidator validator;

    @BeforeAll
    private static void init() {
        context = Mockito.mock(ConstraintValidatorContext.class);
        validator = new PasswordValidator();
    }

    @Test
    void test_isValid_correctPassword_1() {
        helper_isValid_correctPassword("DsddaS24A");
    }

    @Test
    void test_isValid_correctPassword_2() {
        helper_isValid_correctPassword("qwerT1");
    }

    @Test
    void test_isValid_correctPassword_3() {
        helper_isValid_correctPassword("sadsF23");
    }

    @Test
    void test_isValid_incorrectPassword_1() {
        helper_isValid_incorrectPassword("aaa");
    }

    @Test
    void test_isValid_incorrectPassword_2() {
        helper_isValid_incorrectPassword("ppppPP");
    }

    @Test
    void test_isValid_incorrectPassword_3() {
        helper_isValid_incorrectPassword("2313PPP");
    }

    private void helper_isValid_correctPassword(String password) {
        assertTimeout(Duration.ofMillis(TIMEOUT), () -> assertTrue(validator.isValid(password, context)));
    }

    private void helper_isValid_incorrectPassword(String password) {
        assertTimeout(Duration.ofMillis(TIMEOUT), () -> assertFalse(validator.isValid(password, context)));
    }
}