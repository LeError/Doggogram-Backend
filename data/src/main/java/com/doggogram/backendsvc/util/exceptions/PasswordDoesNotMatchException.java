package com.doggogram.backendsvc.util.exceptions;

public class PasswordDoesNotMatchException extends Exception {

    public PasswordDoesNotMatchException() {
        super();
    }

    public PasswordDoesNotMatchException(String message) {
        super(message);
    }

}
