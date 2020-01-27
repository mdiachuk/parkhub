package ua.com.parkhub.validation.validators;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.validation.ConstraintValidatorContext;
import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

class UsreouCodeValidatorTest {

    private static final int TIMEOUT = 2000;
    private static ConstraintValidatorContext context;
    private static UsreouCodeValidator validator;

    @BeforeAll
    private static void init() {
        context = Mockito.mock(ConstraintValidatorContext.class);
        validator = new UsreouCodeValidator();
    }

    @Test
    void test_isValid_correctUsreouCode_1() {
        helper_isValid_correctUsreouCode("32789103");
    }

    @Test
    void test_isValid_correctUsreouCode_2() {
        helper_isValid_correctUsreouCode("34978910");
    }

    @Test
    void test_isValid_correctUsreouCode_3() {
        helper_isValid_correctUsreouCode("56019856");
    }

    @Test
    void test_isValid_incorrectUsreouCode_1() {
        helper_isValid_incorrectUsreouCode("111");
    }

    @Test
    void test_isValid_incorrectUsreouCode_2() {
        helper_isValid_incorrectUsreouCode("231aa213");
    }

    @Test
    void test_isValid_incorrectUsreouCode_3() {
        helper_isValid_incorrectUsreouCode("241901.-");
    }

    private void helper_isValid_correctUsreouCode(String usreouCode) {
        assertTimeout(Duration.ofMillis(TIMEOUT), () -> assertTrue(validator.isValid(usreouCode, context)));
    }

    private void helper_isValid_incorrectUsreouCode(String usreouCode) {
        assertTimeout(Duration.ofMillis(TIMEOUT), () -> assertFalse(validator.isValid(usreouCode, context)));
    }
}