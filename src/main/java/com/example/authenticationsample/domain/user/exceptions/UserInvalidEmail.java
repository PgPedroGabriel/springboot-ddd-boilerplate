package com.example.authenticationsample.domain.user.exceptions;

public class UserInvalidEmail extends Exception {

    public UserInvalidEmail(String message) {
        super(message);
    }
}
