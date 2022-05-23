package com.semicolon.africa.Messaging.System.data.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Mailbox {
    @Id
    private String email;
    private List<Message> message;
    private MailboxType mailboxType;

}
