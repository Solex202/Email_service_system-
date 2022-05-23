package com.semicolon.africa.Messaging.System.data.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Message {
    private String msgId;
    private String sender;
    private String receiver;
    private String body;
    private LocalDateTime localDateTime;
}
