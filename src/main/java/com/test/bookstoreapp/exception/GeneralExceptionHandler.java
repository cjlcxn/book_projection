package com.test.bookstoreapp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

// this is a global error handler, that "beautify" errors before sending it back as JSON
// controllerAdvice is a filter, for all post-process responses
@ControllerAdvice
public class GeneralExceptionHandler {

    // exceptionHandler returns a POJO, for logging exceptions
    @ExceptionHandler
    public ResponseEntity<BookErrorResponse> handleException(MethodArgumentNotValidException exc) {
        BookErrorResponse error = new BookErrorResponse();

        error.setMessage(exc.getMessage());
        error.setStatus(HttpStatus.BAD_REQUEST.value());
        error.setTimeStamp(System.currentTimeMillis());

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    // exception handler for invalid id
    @ExceptionHandler
    public ResponseEntity<BookErrorResponse> handleException(BookInvalidIdExc exc) {
        BookErrorResponse error = new BookErrorResponse();

        error.setMessage(exc.getMessage());
        error.setStatus(HttpStatus.NOT_FOUND.value());
        error.setTimeStamp(System.currentTimeMillis());

        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    // overrides exception for updating id that is x within the scope
    @ExceptionHandler
    public ResponseEntity<BookErrorResponse> handleException(ObjectOptimisticLockingFailureException exc) {
        BookErrorResponse error = new BookErrorResponse();

        error.setMessage("ID entered not in range");
        error.setStatus(HttpStatus.NOT_FOUND.value());
        error.setTimeStamp(System.currentTimeMillis());

        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }


}
