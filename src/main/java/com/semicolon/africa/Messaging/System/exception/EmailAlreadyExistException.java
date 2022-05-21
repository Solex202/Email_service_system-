package com.semicolon.africa.Messaging.System.exception;

public class EmailAlreadyExistException extends RuntimeException{

    public EmailAlreadyExistException(String message){
        super(message);
    }
}
