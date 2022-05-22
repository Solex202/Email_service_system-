package com.semicolon.africa.Messaging.System.services;

import com.semicolon.africa.Messaging.System.data.models.Mailbox;
import com.semicolon.africa.Messaging.System.data.models.MailboxType;
import com.semicolon.africa.Messaging.System.data.models.Message;
import com.semicolon.africa.Messaging.System.data.models.User;
import com.semicolon.africa.Messaging.System.data.repositories.UserRepository;
import com.semicolon.africa.Messaging.System.dtos.request.CreateUserRequest;
import com.semicolon.africa.Messaging.System.dtos.response.UserDto;
import com.semicolon.africa.Messaging.System.exception.EmailAlreadyExistException;
import com.semicolon.africa.Messaging.System.exception.PasswordsMustMatchException;
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
    private MailboxesServiceImpl mailService;

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

        User user = new User();
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());

        Mailbox mailbox = mailBoxService.createMailBox(request.getEmail());
        Message message = new Message();
        message.setSender("default mailing service");
        message.setBody("Welcome to you email service " + request.getEmail());
        mailbox.getMessage().add(message);
        mailbox.setMailboxType(MailboxType.INBOX);
        mailService.createMailbox(request.getEmail(), mailbox);

        repository.save(user);
         return mapper.map(user, UserDto.class);

    }

    private boolean emailAlreadyExist(String email) {
        return  repository.findByEmail(email) != null;
    }

    @Override
    public List<User> getAllUser() {
        return repository.findAll();
    }

    @Override
    public void deleteAll() {
         repository.deleteAll();
    }


}
