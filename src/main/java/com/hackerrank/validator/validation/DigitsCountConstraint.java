package com.hackerrank.validator.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = DigitsCountValidator.class)
@Target({ ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface DigitsCountConstraint {
    String message() default "Invalid number of digits";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
    int value();
}
