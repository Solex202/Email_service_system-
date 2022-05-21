package com.semicolon.africa.Messaging.System.data.repositories;

import com.semicolon.africa.Messaging.System.data.models.Mailboxes;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface MailboxesRepository extends MongoRepository<Mailboxes, String> {
}
