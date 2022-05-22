package com.semicolon.africa.Messaging.System.services;

import com.semicolon.africa.Messaging.System.data.models.Mailbox;

public interface MailBoxService {
    Mailbox createMailBox(String email);
}
