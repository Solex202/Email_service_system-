package com.semicolon.africa.Messaging.System.data.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Mailbox {

    private String message;
    private MailboxType mailboxType;
}
