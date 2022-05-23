package com.semicolon.africa.Messaging.System.services;

import com.semicolon.africa.Messaging.System.data.models.Message;
import com.semicolon.africa.Messaging.System.dtos.request.CreateMessageDto;

public interface MessageService {

    Message sendMessage(CreateMessageDto createMessageDto);
}
