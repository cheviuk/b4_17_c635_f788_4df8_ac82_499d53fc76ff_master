package com.hackerrank.validator.validation;

import com.hackerrank.validator.model.Employee;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class EmployeeValidator implements Validator {

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @Override
    public boolean supports(Class<?> aClass) {
        return Employee.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object employeeObject, Errors errors) {
        Employee employee = (Employee) employeeObject;

        validateFullName(employee, errors);
        validateMobileNumber(employee, errors);
        validateEmailId(employee, errors);
        validateDateOfBirth(employee, errors);
    }

    private void validateFullName(Employee employee, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "fullName", "fullName.empty"
                , "The fullName is a mandatory field");
    }

    private void validateMobileNumber(Employee employee, Errors errors) {
        if (employee.getMobileNumber() == null) {
            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "mobileNumber", "mobileNumber.empty"
                    , "The mobileNumber is a mandatory field");
        } else {
            if (String.valueOf(employee.getMobileNumber()).length() != 10) {
                errors.rejectValue("mobileNumber", "mobileNumber.invalid"
                        , "The mobileNumber is a mandatory field");
            }
        }
    }

    private void validateEmailId(Employee employee, Errors errors) {
        if (employee.getEmailId() == null || employee.getEmailId().isEmpty()) {
            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "emailId"
                    , "emailId.empty", "The emailId is a mandatory field");
        } else {
            if (!employee.getEmailId().contains("@")) {
                errors.rejectValue("emailId", "emailId.invalid"
                        , "The emailId should be in a valid email format");
            }
        }
    }

    private void validateDateOfBirth(Employee employee, Errors errors) {
        if (employee.getDateOfBirth() == null || employee.getDateOfBirth().isEmpty()) {
            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dateOfBirth", "dateOfBirth.empty"
                    , "The dateOfBirth is a mandatory field");
        } else {
            try {
                LocalDate.parse(employee.getDateOfBirth(), DATE_FORMATTER);
            } catch (DateTimeParseException e) {
                errors.rejectValue("dateOfBirth", "dateOfBirth.invalid"
                        , "The dateOfBirth should be in YYYY-MM-DD format");
            }
        }
    }
}
