package com.semicolon.africa.Messaging.System.dtos.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateMessageDto {

    private String sender;
    private String receiver;
    private String body;
    private LocalDateTime localDateTime ;

    public CreateMessageDto(String sender, String receiver, String body) {
        this.sender = sender;
        this.receiver = receiver;
        this.body = body;
        localDateTime = LocalDateTime.now();
    }

}
