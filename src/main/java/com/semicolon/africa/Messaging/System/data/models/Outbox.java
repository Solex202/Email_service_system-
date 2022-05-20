package com.semicolon.africa.Messaging.System.data.models;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Outbox {
    private String msg;
    private LocalDateTime localDateTime;
    private String sender_email;
}
