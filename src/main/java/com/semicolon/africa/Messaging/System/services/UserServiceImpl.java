package com.semicolon.africa.Messaging.System.services;

import com.semicolon.africa.Messaging.System.data.models.Mailbox;
import com.semicolon.africa.Messaging.System.data.models.MailboxType;
import com.semicolon.africa.Messaging.System.data.models.Message;
import com.semicolon.africa.Messaging.System.data.models.User;
import com.semicolon.africa.Messaging.System.data.repositories.UserRepository;
import com.semicolon.africa.Messaging.System.dtos.request.CreateUserRequest;
import com.semicolon.africa.Messaging.System.dtos.request.LoginRequest;
import com.semicolon.africa.Messaging.System.dtos.response.LoginResponse;
import com.semicolon.africa.Messaging.System.dtos.response.UserDto;
import com.semicolon.africa.Messaging.System.exception.EmailAlreadyExistException;
import com.semicolon.africa.Messaging.System.exception.PasswordsMustMatchException;
import com.semicolon.africa.Messaging.System.exception.UserDoesNotExistException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserRepository repository;

    private ModelMapper mapper = new ModelMapper();

    @Autowired
    private MailboxesServiceImpl mailboxesService;

    @Autowired
    private MailBoxServiceImpl mailBoxService;

    @Override
    public UserDto createUser(CreateUserRequest request) {
        if(emailAlreadyExist(request.getEmail())){
            throw new EmailAlreadyExistException("email already exist");
        }
        if(!request.getPassword().matches(request.getConfirmPassword())){
            throw new PasswordsMustMatchException("passwords must match");
        }

        User user = User.builder().email(request.getEmail()).password(request.getPassword()).build();

        repository.save(user);
        Mailbox mailbox = mailBoxService.createMailBox(request.getEmail());
        Message message = new Message();
        message.setSender("default mailing service");
        message.setBody("Welcome to you email service " + request.getEmail());
//        mailbox.getMessage().add(message);
        mailbox.setMailboxType(MailboxType.INBOX);
        mailboxesService.createMailbox(request.getEmail(), mailbox);
         return mapper.map(user, UserDto.class);
    }

    private boolean emailAlreadyExist(String email) {
        return repository.findByEmail(email).isPresent();
    }

    @Override
    public List<User> getAllUser() {
        return repository.findAll();
    }

    @Override
    public void deleteAll() {
         repository.deleteAll();
    }

    @Override
    public LoginResponse login(LoginRequest loginRequest) {
        if(userDoesNotExistInDatabase(loginRequest.getEmail())){
            throw new UserDoesNotExistException("user does not exist, please create an account");
        }
        User user =  User.builder().email(loginRequest.getEmail()).password(loginRequest.getPassword()).loginStatus(true).build();

        LoginResponse loginResponse = new LoginResponse();
        repository.save(user);
        loginResponse.setMessage("login successful");

        return loginResponse;
        //TODO edit
    }

    private boolean userDoesNotExistInDatabase(String email) {
        return !repository.findByEmail(email).isPresent();
    }


}
