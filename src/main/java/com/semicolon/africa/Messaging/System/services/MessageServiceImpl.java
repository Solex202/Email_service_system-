package com.semicolon.africa.Messaging.System.services;

import com.semicolon.africa.Messaging.System.data.models.Message;
import com.semicolon.africa.Messaging.System.data.models.Notification;
import com.semicolon.africa.Messaging.System.data.models.User;
import com.semicolon.africa.Messaging.System.data.repositories.MessageRepository;
import com.semicolon.africa.Messaging.System.data.repositories.UserRepository;
import com.semicolon.africa.Messaging.System.dtos.request.CreateMessageDto;
import com.semicolon.africa.Messaging.System.exception.UserDoesNotExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class MessageServiceImpl implements MessageService{

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private UserRepository userRepository;
    @Override
    public Message sendMessage(CreateMessageDto createMessageDto) {

        Message newMessage = Message.builder()
                .sender(createMessageDto.getSender())
                .receiver(createMessageDto.getReceiver())
                .localDateTime(createMessageDto.getLocalDateTime())
                .body(createMessageDto.getBody())
                .build();

        User recipient = userRepository.findByEmail(createMessageDto.getReceiver()).orElseThrow(() -> new UserDoesNotExistException("receiver not founder"));
        Notification notification = Notification.builder()
                .message(createMessageDto.getBody())
                .email(createMessageDto.getSender())
                .title("new message alert ")
                .id(newMessage.getMsgId())
                .build();
        recipient.getNotifications().add(notification);
        userRepository.save(recipient);

        return messageRepository.save(newMessage);
    }
}
