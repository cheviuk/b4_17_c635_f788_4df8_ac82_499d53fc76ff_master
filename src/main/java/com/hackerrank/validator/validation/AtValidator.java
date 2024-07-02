package com.hackerrank.validator.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class AtValidator implements ConstraintValidator<AtConstraint, String> {

    @Override
    public void initialize(AtConstraint constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null || value.equals("")) {
            return true; // skip because mandatory constraint already failed or will be failed
        }
        CharSequence at = "@";
        return value.contains(at);
    }
}
