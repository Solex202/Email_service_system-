package com.semicolon.africa.Messaging.System.dtos.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Min;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Validated
public class CreateUserRequest {

    private String email;
    @Min(6)
    private String password;
    @Min(6)
    private String confirmPassword;
}
