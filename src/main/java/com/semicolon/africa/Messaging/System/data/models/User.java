package com.semicolon.africa.Messaging.System.data.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;


@Data
@NoArgsConstructor
@AllArgsConstructor@Builder
public class User {

    @Id
    private String email;
    private String password;
    private boolean loginStatus;
}
