package com.semicolon.africa.Messaging.System.services;

import com.semicolon.africa.Messaging.System.data.models.Mailbox;
import com.semicolon.africa.Messaging.System.data.models.MailboxType;
import com.semicolon.africa.Messaging.System.data.models.Mailboxes;
import com.semicolon.africa.Messaging.System.data.repositories.MailboxesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class MailboxesServiceImpl implements MailboxesService{

    @Autowired
    private MailboxesRepository mailboxesRepository;

    @Override
    public Mailboxes createMailbox(String email, Mailbox mailbox) {

        Mailboxes mailboxes = new Mailboxes();
        mailboxes.setEmail(email);
        mailboxes.setMailboxes(new ArrayList<>());

        Mailbox sentBox = new Mailbox();
//        sentBox.setMailboxType(MailboxType.SENT);
        mailboxes.getMailboxes().add(sentBox);

        Mailbox inbox = new Mailbox();
//        inbox.setMailboxType(MailboxType.INBOX);
        mailboxes.getMailboxes().add(inbox);
        mailboxesRepository.save(mailboxes);

        return mailboxes;
    }
}
