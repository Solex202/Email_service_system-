package com.semicolon.africa.Messaging.System.dtos;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ApiResponse {

    private String message;
    private  boolean isSuccessful;
}
