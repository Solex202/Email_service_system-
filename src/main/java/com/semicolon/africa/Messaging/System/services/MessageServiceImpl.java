package com.semicolon.africa.Messaging.System.services;

import com.semicolon.africa.Messaging.System.data.models.Message;
import com.semicolon.africa.Messaging.System.data.repositories.MessageRepository;
import com.semicolon.africa.Messaging.System.dtos.request.CreateMessageDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class MessageServiceImpl implements MessageService{

    @Autowired
    private MessageRepository messageRepository;
    @Override
    public Message sendMessage(CreateMessageDto createMessageDto) {

        Message newMessage = Message.builder()
                .sender(createMessageDto.getSender())
                .receiver(createMessageDto.getReceiver())
                .localDateTime(createMessageDto.getLocalDateTime())
                .body(createMessageDto.getBody())
                .build();
        return messageRepository.save(newMessage);
    }
}
