package com.hackerrank.validator.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class MandatoryValidator implements ConstraintValidator<MandatoryConstraint, Object> {

    @Override
    public void initialize(MandatoryConstraint constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        if (value == null || value.toString().equals("")) {
            return false;
        }
        return true;
    }
}
