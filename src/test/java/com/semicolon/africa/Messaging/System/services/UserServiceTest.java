package com.semicolon.africa.Messaging.System.services;

import com.semicolon.africa.Messaging.System.dtos.request.CreateUserRequest;
import com.semicolon.africa.Messaging.System.dtos.request.LoginRequest;
import com.semicolon.africa.Messaging.System.dtos.response.LoginResponse;
import com.semicolon.africa.Messaging.System.dtos.response.UserDto;
import com.semicolon.africa.Messaging.System.exception.EmailAlreadyExistException;
import com.semicolon.africa.Messaging.System.exception.UserDoesNotExistException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;
@DataMongoTest
class UserServiceTest {

    @Autowired
    private UserService userService;

    @BeforeEach
    void setUp() {
    }

    @Test
    void testThatCanCreateUser(){
        CreateUserRequest request = CreateUserRequest.builder().email("mercy@gmail.com").password("mercy4Life123").confirmPassword("mercy4Life123").build();
        userService.createUser(request);
        assertThat(userService.getAllUser().size(), is(1));
    }

    @Test
    void testThatUserCannotCreateAccountWithEmailThatAlreadyExist_exception(){
        CreateUserRequest request = CreateUserRequest.builder().email("mercy@gmail.com").password("mercy4Life123").confirmPassword("mercy4Life123").build();
        userService.createUser(request);

        CreateUserRequest request2 = CreateUserRequest.builder().email("mercy@gmail.com").password("ruth4@!34weRe").confirmPassword("ruth4@!34weRe").build();

        assertThrows(EmailAlreadyExistException.class, ()-> userService.createUser(request2));
    }

    @Test
    void testThatResponseCanBeGotten(){
        CreateUserRequest request = CreateUserRequest.builder().email("mercy@gmail.com").password("mercy4Life123").confirmPassword("mercy4Life123").build();
        UserDto userDto = userService.createUser(request);

        assertThat(userDto.getEmail(), is("mercy@gmail.com"));
        assertThat(userDto.getMessage(),is("user created"));
    }

    @Test
    void testThatUserCanLoginBeforeAccessingApp(){
        CreateUserRequest request = CreateUserRequest.builder().email("mercy@gmail.com").password("mercy4Life123").confirmPassword("mercy4Life123").build();
        UserDto userDto = userService.createUser(request);
        assertThat(userDto.getEmail(), is("mercy@gmail.com"));

        LoginRequest loginRequest = LoginRequest.builder().email("mercy@gmail.com").password("mercy4Life123").build();
        LoginResponse loginResponse = userService.login(loginRequest);
        assertThat(loginResponse.getMessage(),is("login successful"));
    }

    @Test
    void testThatUnregisteredUserCannotLogin_throwException(){
        LoginRequest loginRequest = LoginRequest.builder().email("mercy@gmail.com").password("mercy4Life123").build();
        assertThrows(UserDoesNotExistException.class,()-> userService.login(loginRequest));
    }

    @AfterEach
    void tearDown() {
        userService.deleteAll();
    }
}