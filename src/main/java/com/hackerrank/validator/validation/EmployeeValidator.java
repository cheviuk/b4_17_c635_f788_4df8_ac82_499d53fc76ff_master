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

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "fullName", "fullName.empty"
                , "The fullName is a mandatory field");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "mobileNumber", "mobileNumber.empty"
                , "The mobileNumber is a mandatory field");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "emailId"
                , "emailId.empty", "The emailId is a mandatory field");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dateOfBirth", "dateOfBirth.empty"
                , "The dateOfBirth is a mandatory field");

        if (employee.getMobileNumber() != null && String.valueOf(employee.getMobileNumber()).length() != 10) {
            errors.rejectValue("mobileNumber", "mobileNumber.invalid"
                    , "The mobileNumber is a mandatory field");
        }

        if (employee.getEmailId() != null
                && !employee.getEmailId().equals("")
                && !employee.getEmailId().contains("@")) {
            errors.rejectValue("emailId", "emailId.invalid"
                    , "The emailId should be in a valid email format");
        }

        if (employee.getDateOfBirth() != null && !employee.getDateOfBirth().equals("")) {
            try {
                LocalDate.parse(employee.getDateOfBirth(), DATE_FORMATTER);
            } catch (DateTimeParseException e) {
                errors.rejectValue("dateOfBirth", "dateOfBirth.invalid"
                        , "The dateOfBirth should be in YYYY-MM-DD format");
            }
        }
    }
}
