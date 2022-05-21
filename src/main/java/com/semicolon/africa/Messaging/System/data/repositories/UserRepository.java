package com.semicolon.africa.Messaging.System.data.repositories;

import com.semicolon.africa.Messaging.System.data.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
    User findByEmail(String email);
}
