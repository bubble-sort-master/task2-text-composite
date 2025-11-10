package com.innowise.task2.exception;

public class TextException extends Exception {
    public TextException() {
        super();
    }

    public TextException(String message) {
        super(message);
    }

    public TextException(String message, Throwable cause) {
        super(message, cause);
    }

    public TextException(Throwable cause) {
        super(cause);
    }

}
