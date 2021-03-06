package com.semicolon.africa.Messaging.System.services;

import com.semicolon.africa.Messaging.System.data.models.*;
import com.semicolon.africa.Messaging.System.data.repositories.MailboxRepository;
import com.semicolon.africa.Messaging.System.dtos.request.CreateMessageDto;
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

import java.util.List;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.*;
@DataMongoTest
class UserServiceTest {

    @Autowired
    private UserService userService;

    @Autowired
    private MessageService messageService;

    @Autowired
    private MailboxRepository mailboxRepository;

    @BeforeEach
    void setUp() {
    }

    @Test
    void testThatCanCreateUser(){
        CreateUserRequest request = CreateUserRequest.builder()
                .email("mercy@gmail.com")
                .password("mercy4Life123")
                .confirmPassword("mercy4Life123")
                .build();
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
//        assertThat(userDto.getMessage(),is("user created"));
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


    @Test
    void testThatAUserCanSendAMessage(){
        CreateUserRequest sender = CreateUserRequest.builder().email("mercy@gmail.com").password("mercy4Life123").confirmPassword("mercy4Life123").build();
        UserDto userDto = userService.createUser(sender);
        assertThat(userDto.getEmail(), is("mercy@gmail.com"));

        //sender login
        LoginRequest senderLoginRequest = LoginRequest.builder().email("mercy@gmail.com").password("mercy4Life123").build();
        LoginResponse loginResponse = userService.login(senderLoginRequest);
        assertThat(loginResponse.getMessage(),is("login successful"));

        CreateUserRequest receiver = CreateUserRequest.builder().email("deola@gmail.com").password("deolaDeji").confirmPassword("deolaDeji").build();
        UserDto userDto2 = userService.createUser(receiver);
        assertThat(userDto2.getEmail(), is("deola@gmail.com"));


        LoginRequest receiverLoginRequest = LoginRequest.builder().email("deola@gmail.com").password("deolaDeji").build();
        LoginResponse loginResponse2 = userService.login(receiverLoginRequest);
        assertThat(loginResponse2.getMessage(), is("login successful"));

        List<User> users = userService.getAllUser();
        assertThat(users.size(), equalTo(2));

        CreateMessageDto createMessageDto = new CreateMessageDto(sender.getEmail(), receiver.getEmail(), "My first email to you");

        Message response = messageService.sendMessage(createMessageDto);
        assertThat(response.getMsg(), is("message sent"));

    }

    @Test
    void testThatReceiverCanSeeSentMessages(){
        CreateUserRequest sender = CreateUserRequest.builder().email("mercy@gmail.com").password("mercy4Life123").confirmPassword("mercy4Life123").build();
        UserDto userDto = userService.createUser(sender);
        assertThat(userDto.getEmail(), is("mercy@gmail.com"));

        //sender login
        LoginRequest senderLoginRequest = LoginRequest.builder().email("mercy@gmail.com").password("mercy4Life123").build();
        LoginResponse loginResponse = userService.login(senderLoginRequest);
        assertThat(loginResponse.getMessage(),is("login successful"));

        CreateUserRequest receiver = CreateUserRequest.builder().email("deola@gmail.com").password("deolaDeji").confirmPassword("deolaDeji").build();
        UserDto userDto2 = userService.createUser(receiver);
        assertThat(userDto2.getEmail(), is("deola@gmail.com"));


        LoginRequest receiverLoginRequest = LoginRequest.builder().email("deola@gmail.com").password("deolaDeji").build();
        LoginResponse loginResponse2 = userService.login(receiverLoginRequest);
        assertThat(loginResponse2.getMessage(), is("login successful"));

        List<User> users = userService.getAllUser();
        assertThat(users.size(), equalTo(2));

        CreateMessageDto createMessageDto = new CreateMessageDto(sender.getEmail(), receiver.getEmail(), "My first email to you");
        CreateMessageDto createMessageDto2 = new CreateMessageDto(sender.getEmail(), receiver.getEmail(), "My second email to you");

        Message response = messageService.sendMessage(createMessageDto);
        Message response2 = messageService.sendMessage(createMessageDto2);
        assertThat(response.getMsg(), is("message sent"));
        assertThat(response2.getMsg(), is("message sent"));

        Optional<Mailbox> mailboxes =mailboxRepository.findById(receiver.getEmail());
        System.out.println(mailboxes.get());
        if(mailboxes.get().getMailboxType() == MailboxType.INBOX) {
            assertThat(mailboxes.get().getMessage().size(), equalTo(2));
        }
        assertThat(mailboxes.get().getMessage().size(), equalTo(2));


    }

    @AfterEach
    void tearDown() {
        userService.deleteAll();
    }
}