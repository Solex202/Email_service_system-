package com.semicolon.africa.Messaging.System.data.models;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Message {
    private String sender;
    private String receiver;
    private int id;
    private LocalDateTime localDateTime;
}
