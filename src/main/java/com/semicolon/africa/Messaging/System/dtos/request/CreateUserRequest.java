package com.semicolon.africa.Messaging.System.dtos.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreateUserRequest {

    private String email;
    private String password;
    private String confirmPassword;
}
