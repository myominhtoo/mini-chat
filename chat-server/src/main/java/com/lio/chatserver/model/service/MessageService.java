package com.lio.chatserver.model.service;

import com.lio.chatserver.model.repo.MessageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("messageService")
public class MessageService {
  
    private MessageRepo messageRepo;

    @Autowired
    public MessageService( MessageRepo messageRepo ){
        this.messageRepo = messageRepo;
    }

}