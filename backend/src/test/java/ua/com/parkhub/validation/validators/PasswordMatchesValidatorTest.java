package ua.com.parkhub.validation.validators;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ua.com.parkhub.dto.ManagerRegistrationDataDTO;

import javax.validation.ConstraintValidatorContext;
import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

class PasswordMatchesValidatorTest {

    private static final int TIMEOUT = 2000;
    private static ConstraintValidatorContext context;
    private static PasswordMatchesValidator validator;
    private static ManagerRegistrationDataDTO managerWithMatchingPasswords;
    private static ManagerRegistrationDataDTO managerWithNotMatchingPasswords;

    @BeforeAll
    private static void init() {
        context = Mockito.mock(ConstraintValidatorContext.class);
        validator = new PasswordMatchesValidator();
        managerWithMatchingPasswords = Mockito.mock(ManagerRegistrationDataDTO.class);
        Mockito.when(managerWithMatchingPasswords.getPassword()).thenReturn("qwerT1");
        Mockito.when(managerWithMatchingPasswords.getMatchingPassword()).thenReturn("qwerT1");
        managerWithNotMatchingPasswords = Mockito.mock(ManagerRegistrationDataDTO.class);
        Mockito.when(managerWithNotMatchingPasswords.getPassword()).thenReturn("qwerT1");
        Mockito.when(managerWithNotMatchingPasswords.getMatchingPassword()).thenReturn("zxcvb5B");
    }

    @Test
    public void test_isValid_matchingPasswords() {
        assertTimeout(Duration.ofMillis(TIMEOUT), () -> assertTrue(validator.isValid(managerWithMatchingPasswords, context)));
    }

    @Test
    public void test_isValid_notMatchingPasswords() {
        assertTimeout(Duration.ofMillis(TIMEOUT), () -> assertFalse(validator.isValid(managerWithNotMatchingPasswords, context)));
    }
}