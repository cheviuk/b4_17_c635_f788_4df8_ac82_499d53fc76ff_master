package com.hackerrank.validator.validation;

public class FieldValidationMessage implements Comparable<FieldValidationMessage> {
    private String message;

    private int order;

    public FieldValidationMessage(String message, int order) {
        this.message = message;
        this.order = order;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public int getOrder() {
        return order;
    }

    @Override
    public int compareTo(FieldValidationMessage fieldValidationMessage) {
        return Integer.compare(this.getOrder(), fieldValidationMessage.getOrder());
    }
}
