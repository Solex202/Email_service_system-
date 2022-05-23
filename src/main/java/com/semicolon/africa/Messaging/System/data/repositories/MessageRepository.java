package com.semicolon.africa.Messaging.System.data.repositories;

import com.semicolon.africa.Messaging.System.data.models.Message;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface MessageRepository extends MongoRepository<Message, String> {
}
