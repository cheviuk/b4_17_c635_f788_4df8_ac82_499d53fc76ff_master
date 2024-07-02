package com.hackerrank.validator.model;

import com.hackerrank.validator.validation.AtConstraint;
import com.hackerrank.validator.validation.DateFormatConstraint;
import com.hackerrank.validator.validation.DigitsCountConstraint;
import com.hackerrank.validator.validation.MandatoryConstraint;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import org.springframework.core.annotation.Order;

@Entity
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Order(1)
    @MandatoryConstraint(message = "The fullName is a mandatory field")
    private String fullName;

    @Order(2)
    @MandatoryConstraint(message = "The mobileNumber is a mandatory field")
    @DigitsCountConstraint(value = 10, message = "The mobileNumber is a mandatory field")
    private Long mobileNumber;

    @Order(3)
    @MandatoryConstraint(message = "The emailId is a mandatory field")
    @AtConstraint
    private String emailId;

    @Order(4)
    @MandatoryConstraint(message = "The dateOfBirth is a mandatory field")
    @DateFormatConstraint(value = "YYYY-MM-DD", message = "The dateOfBirth should be in YYYY-MM-DD format")
    private String dateOfBirth;

    public Employee() {
    }

    public Employee(String fullName, Long mobileNumber, String emailId, String dateOfBirth) {
        this.fullName = fullName;
        this.mobileNumber = mobileNumber;
        this.emailId = emailId;
        this.dateOfBirth = dateOfBirth;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Long getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(Long mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
}
