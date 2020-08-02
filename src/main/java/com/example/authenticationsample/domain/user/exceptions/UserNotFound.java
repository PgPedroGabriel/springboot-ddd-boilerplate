package com.example.authenticationsample.domain.user.exceptions;

public class UserNotFound extends Exception {
    public UserNotFound(String message) {
        super(message);
    }
}
