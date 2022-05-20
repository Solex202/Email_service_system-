package com.semicolon.africa.Messaging.System.data.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Email
    private String email;

    private String password;
    private String confirmPassword;
    private List<Inbox> inboxes;
    private List<Outbox> outboxes;
    private String fullName;
}
