package com.semicolon.africa.Messaging.System.services;

import com.semicolon.africa.Messaging.System.data.models.User;
import com.semicolon.africa.Messaging.System.dtos.request.CreateUserRequest;
import com.semicolon.africa.Messaging.System.dtos.request.LoginRequest;
import com.semicolon.africa.Messaging.System.dtos.response.LoginResponse;
import com.semicolon.africa.Messaging.System.dtos.response.UserDto;

import java.util.List;

public interface UserService {


    UserDto createUser(CreateUserRequest request);

    List<User> getAllUser();

    void deleteAll();

    LoginResponse login(LoginRequest loginRequest);
}
