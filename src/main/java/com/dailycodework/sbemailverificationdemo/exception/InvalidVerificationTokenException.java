package com.dailycodework.sbemailverificationdemo.exception;


public class InvalidVerificationTokenException extends RuntimeException {
    public InvalidVerificationTokenException(String message) {
        super(message);
    }
}
