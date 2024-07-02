package com.hackerrank.validator.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class DigitsCountValidator implements ConstraintValidator<DigitsCountConstraint, Long> {

    private int numberOfDigits;
    @Override
    public void initialize(DigitsCountConstraint constraintAnnotation) {
        this.numberOfDigits = constraintAnnotation.value();
    }

    @Override
    public boolean isValid(Long value, ConstraintValidatorContext context) {
        if (value == null) {
            return true; // skip because mandatory constraint already failed or will be failed
        }
        return String.valueOf(value).length() == numberOfDigits;
    }
}
