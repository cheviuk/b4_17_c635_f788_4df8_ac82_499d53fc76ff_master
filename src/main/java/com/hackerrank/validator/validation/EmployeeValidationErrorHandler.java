package com.hackerrank.validator.validation;

import com.hackerrank.validator.model.Employee;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class EmployeeValidationErrorHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleBindException(BindException validationException, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        List<FieldValidationMessage> validationErrors =
                validationException
                        .getBindingResult()
                        .getFieldErrors()
                        .stream()
                        .map(fieldError -> {
                            String message = fieldError.getDefaultMessage();
                            int order = 0;
                            try {
                                order = Employee.class.getDeclaredField(fieldError.getField())
                                        .getAnnotation(org.springframework.core.annotation.Order.class).value();
                            } catch (NoSuchFieldException exception) {
                                order = Integer.MAX_VALUE;
                            }
                            return new FieldValidationMessage(message, order);
                        })
                        .sorted()
                        .collect(Collectors.toList());

        return new ResponseEntity<>(validationErrors, HttpStatus.BAD_REQUEST);
    }
}
