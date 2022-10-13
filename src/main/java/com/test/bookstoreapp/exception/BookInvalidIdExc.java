package com.test.bookstoreapp.exception;

public class BookInvalidIdExc extends RuntimeException {
    public BookInvalidIdExc() {
    }

    public BookInvalidIdExc(String message) {
        super(message);
    }

    public BookInvalidIdExc(String message, Throwable cause) {
        super(message, cause);
    }
}
