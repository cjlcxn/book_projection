package com.test.bookstoreapp.exception;

import org.springframework.validation.FieldError;

import java.util.ArrayList;
import java.util.List;

public class BookValidatorResponse {
    private int status;
    private String message;
    private long timeStamp;
    private List<FieldError> fieldErrors;

    public BookValidatorResponse() {
        this.fieldErrors = new ArrayList<>();
    }

    public BookValidatorResponse(int status, String message, long timeStamp) {
        this.status = status;
        this.message = message;
        this.timeStamp = timeStamp;

    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }

    public List<FieldError> getFieldErrors() {
        return fieldErrors;
    }

    public void addFieldError(String path, String message) {
        FieldError error = new FieldError("Book", path, message);
        fieldErrors.add(error);
    }
}
