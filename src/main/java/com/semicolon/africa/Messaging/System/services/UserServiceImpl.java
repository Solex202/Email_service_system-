package com.semicolon.africa.Messaging.System.services;

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
//        UserDto userDto = new UserDto();
//        userDto.setMessage("user created");
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
