package ua.com.parkhub.validation.validators;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ua.com.parkhub.dto.UserDTO;

import javax.validation.ConstraintValidatorContext;
import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

class PasswordMatchesValidatorTest {

    private static final int TIMEOUT = 2000;
    private static ConstraintValidatorContext context;
    private static PasswordMatchesValidator validator;
    private static UserDTO userWithMatchingPasswords;
    private static UserDTO userWithNotMatchingPasswords;

    @BeforeAll
    private static void init() {
        context = Mockito.mock(ConstraintValidatorContext.class);
        validator = new PasswordMatchesValidator();
        userWithMatchingPasswords = Mockito.mock(UserDTO.class);
        Mockito.when(userWithMatchingPasswords.getPassword()).thenReturn("qwerT1");
        Mockito.when(userWithMatchingPasswords.getMatchingPassword()).thenReturn("qwerT1");
        userWithNotMatchingPasswords = Mockito.mock(UserDTO.class);
        Mockito.when(userWithNotMatchingPasswords.getPassword()).thenReturn("qwerT1");
        Mockito.when(userWithNotMatchingPasswords.getMatchingPassword()).thenReturn("zxcvb5B");
    }

    @Test
    public void test_isValid_matchingPasswords() {
        assertTimeout(Duration.ofMillis(TIMEOUT), () -> assertTrue(validator.isValid(userWithMatchingPasswords, context)));
    }

    @Test
    public void test_isValid_notMatchingPasswords() {
        assertTimeout(Duration.ofMillis(TIMEOUT), () -> assertFalse(validator.isValid(userWithNotMatchingPasswords, context)));
    }
}