package com.marvisx.backend.handleException;

public class BookNotFoundException extends RuntimeException  {
    public BookNotFoundException(String message) {
        super(message);

    }
}
