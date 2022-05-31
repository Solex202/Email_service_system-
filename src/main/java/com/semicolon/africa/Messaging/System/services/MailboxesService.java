package com.semicolon.africa.Messaging.System.services;

import com.semicolon.africa.Messaging.System.data.models.Mailbox;
import com.semicolon.africa.Messaging.System.data.models.Mailboxes;

public interface MailboxesService {

     Mailboxes createMailbox(String email);
}
