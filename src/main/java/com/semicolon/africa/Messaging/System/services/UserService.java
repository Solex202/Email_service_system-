package com.semicolon.africa.Messaging.System.services;

import com.semicolon.africa.Messaging.System.data.models.User;
import com.semicolon.africa.Messaging.System.dtos.request.CreateUserRequest;

import java.util.List;

public interface UserService {


    void createUser(CreateUserRequest request);

    List<User> getAllUser();

    void deleteAll();
}
