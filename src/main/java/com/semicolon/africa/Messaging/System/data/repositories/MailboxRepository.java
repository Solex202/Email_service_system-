package com.semicolon.africa.Messaging.System.data.repositories;

import com.semicolon.africa.Messaging.System.data.models.Mailbox;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MailboxRepository extends MongoRepository<Mailbox, String > {

}
