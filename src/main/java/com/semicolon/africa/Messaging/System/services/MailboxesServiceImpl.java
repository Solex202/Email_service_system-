package com.semicolon.africa.Messaging.System.services;

import com.semicolon.africa.Messaging.System.data.models.Mailbox;
import com.semicolon.africa.Messaging.System.data.models.MailboxType;
import com.semicolon.africa.Messaging.System.data.models.Mailboxes;
import com.semicolon.africa.Messaging.System.data.models.Message;
import com.semicolon.africa.Messaging.System.data.repositories.MailboxesRepository;
import com.semicolon.africa.Messaging.System.dtos.request.CreateMessageDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MailboxesServiceImpl implements MailboxesService{

    @Autowired
    private MailboxesRepository mailboxesRepository;

    @Autowired
    private MessageService messageService;

    @Override
    public Mailboxes createMailbox(String email, Mailbox mailbox) {

        Mailboxes mailboxes = new Mailboxes();
        mailboxes.setEmail(email);
        mailboxes.setMailboxes(new ArrayList<>());

        Mailbox inbox = new Mailbox();
        inbox.setMailboxType(MailboxType.INBOX);
//        mailboxes.getMailboxes().add(inbox);

        CreateMessageDto createMessageDto = new CreateMessageDto("mail sender",email,"welcome to our mail service");
        Message createdMsg = messageService.sendMessage(createMessageDto);
        inbox.setMessage(List.of(createdMsg));
        mailboxes.getMailboxes().add(inbox);

        Mailbox sentBox = new Mailbox();
        sentBox.setMailboxType(MailboxType.SENT);
        mailboxes.getMailboxes().add(sentBox);

        mailboxesRepository.save(mailboxes);

        return mailboxes;
    }
}
