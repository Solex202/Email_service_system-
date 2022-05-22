package com.semicolon.africa.Messaging.System.dtos.response;


import lombok.Data;

@Data
public class UserDto {

    private String message;
    private String email;
    private boolean loginStatus;
}
