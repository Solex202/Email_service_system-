package com.semicolon.africa.Messaging.System.services;

import com.semicolon.africa.Messaging.System.data.models.Mailbox;
import com.semicolon.africa.Messaging.System.data.repositories.MailboxRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MailBoxServiceImpl implements MailBoxService{
    @Autowired
    MailboxRepository mailboxRepository;

    @Override
    public Mailbox createMailBox(String email) {
        Mailbox mailbox = new Mailbox();
        mailbox.setEmail(email);


        mailboxRepository.save(mailbox);
        return mailbox;
    }
}
