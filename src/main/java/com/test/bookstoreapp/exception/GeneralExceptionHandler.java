package com.test.bookstoreapp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.List;

// this is a global error handler, that "beautify" errors before sending it back as JSON
// controllerAdvice is a filter, for all post-process responses
@ControllerAdvice
public class GeneralExceptionHandler {

    // exceptionHandler returns a POJO, for logging exceptions
    // GOOD PRACTICE TO HAVE @ResponseStatus and @ResponseBody and @ExceptionHandler shud have the exp class type.
    @ExceptionHandler
    public ResponseEntity<BookValidatorResponse> handleException(MethodArgumentNotValidException exc) {
        // binding result is to retrieve data of validation error, that is added as a ppt in MethodArgumentNotValidException
        BindingResult results = exc.getBindingResult();
        // the BindingResult object, has a list of FieldErrors as ppt, and each error is each validation failure
        List<FieldError> fieldErrors = results.getFieldErrors();

        // this response object, is built to accomodate another ppt, i.e. a list of FieldErrors!
        BookValidatorResponse error = new BookValidatorResponse();

        error.setMessage("validation error!");
        error.setStatus(HttpStatus.BAD_REQUEST.value());
        error.setTimeStamp(System.currentTimeMillis());

        // for each fieldError in list, we extract it, and use helper function to add the error in.
        for(FieldError fielderror:fieldErrors) {
            error.addFieldError(fielderror.getField(), fielderror.getDefaultMessage());
        }

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

    @ExceptionHandler
    public ResponseEntity<BookErrorResponse> handleException(MethodArgumentTypeMismatchException exc) {
        BookErrorResponse error = new BookErrorResponse();

        error.setMessage("Only Integers allowed, as parameter value!");
        error.setStatus(HttpStatus.BAD_REQUEST.value());
        error.setTimeStamp(System.currentTimeMillis());

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }




}
