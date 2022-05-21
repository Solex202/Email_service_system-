package com.semicolon.africa.Messaging.System.data.models;


import lombok.Data;

@Data
public class Mailbox {

    private String message;
    private MailboxType mailboxType;
}
