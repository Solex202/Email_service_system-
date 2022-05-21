package com.semicolon.africa.Messaging.System.controllers;

import com.semicolon.africa.Messaging.System.dtos.ApiResponse;
import com.semicolon.africa.Messaging.System.dtos.request.CreateUserRequest;
import com.semicolon.africa.Messaging.System.exception.EmailAlreadyExistException;
import com.semicolon.africa.Messaging.System.exception.PasswordsMustMatchException;
import com.semicolon.africa.Messaging.System.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/emailApp")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/users/create")
    public ResponseEntity<?> createUser(@RequestBody CreateUserRequest request){
        try{

            ApiResponse response = ApiResponse.builder()
                    .message("id:" + userService.createUser(request))
                    .isSuccessful(true).build();
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (PasswordsMustMatchException  | EmailAlreadyExistException ex){

            ApiResponse response = ApiResponse.builder()
                    .message(ex.getMessage())
                    .isSuccessful(false)
                    .build();
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

}
