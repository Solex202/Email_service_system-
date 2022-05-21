package com.semicolon.africa.Messaging.System.exception;

public class PasswordsMustMatchException extends RuntimeException {

    public PasswordsMustMatchException(String message){
        super(message);
    }
}
