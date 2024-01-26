package com.TOSAN.onlineBookStore.exception;

public class CustomErrorException extends Exception {

    public CustomErrorException() {
        super();
    }

    public CustomErrorException(Throwable e) {
        super(e);
    }

    public CustomErrorException(String message) {
        super(message);
    }

    public CustomErrorException(String message, Throwable cause) {
        super(message, cause);
    }
}
