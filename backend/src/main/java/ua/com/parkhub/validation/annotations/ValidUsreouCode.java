package ua.com.parkhub.validation.annotations;

import ua.com.parkhub.validation.validators.UsreouCodeValidator;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Target({TYPE, FIELD, ANNOTATION_TYPE})
@Retention(RUNTIME)
@Constraint(validatedBy = UsreouCodeValidator.class)
public @interface ValidUsreouCode {

    String message() default "Invalid USREOU code";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
