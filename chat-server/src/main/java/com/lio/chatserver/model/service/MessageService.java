package com.lio.chatserver.model.service;

import com.lio.chatserver.model.entity.Message;
import com.lio.chatserver.model.repo.MessageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service("messageService")
public class MessageService {
  
    private MessageRepo messageRepo;

    @Autowired
    public MessageService( MessageRepo messageRepo ){
        this.messageRepo = messageRepo;
    }

    public Message saveMesage( Message message ){
        message.setSentDate(LocalDateTime.now());
        return this.messageRepo.save(message);
    }

    public List<Message> getMessages(){
        return this.messageRepo.findAll();
    }

}