package com.semicolon.africa.Messaging.System.exception;

public class UserDoesNotExistException extends RuntimeException{

    public UserDoesNotExistException(String message){
        super(message);
    }
}
