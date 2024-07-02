package com.hackerrank.validator.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateFormatValidator implements ConstraintValidator<DateFormatConstraint, String> {

    private String dateFormat;

    @Override
    public void initialize(DateFormatConstraint constraintAnnotation) {
        this.dateFormat = constraintAnnotation.value();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null || value.equals("")) {
            return true; // skip because mandatory constraint already failed or will be failed
        }
        try {
            LocalDate.parse(value, DateTimeFormatter.ofPattern(dateFormat));
        } catch (DateTimeParseException e) {
            return false;
        }
        return true;
    }
}
