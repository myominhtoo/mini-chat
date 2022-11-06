package com.lio.chatserver.controller;

import com.lio.chatserver.model.entity.Message;
import com.lio.chatserver.model.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class ChatController {

    private MessageService messageService;
    private SimpMessagingTemplate simpMessagingTemplate;

    @Autowired
    public ChatController( MessageService messageService , SimpMessagingTemplate simpMessagingTemplate ){
        this.messageService = messageService;
        this.simpMessagingTemplate = simpMessagingTemplate;
    }

    @MessageMapping( value = "/send-message" )
    @SendTo( "/messages" )
    public Message sendMessage( @Payload Message message  ){
       return this.messageService.saveMesage(message);
    }

}