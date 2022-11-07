package com.lio.chatserver.controller;

import com.lio.chatserver.model.entity.Message;
import com.lio.chatserver.model.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping( value = "/api/v1" )
@CrossOrigin( originPatterns =  "*" )
public class MessageController {

    private MessageService messageService;

    @Autowired
    public MessageController( MessageService messageService ){
        this.messageService = messageService;
    }

    @GetMapping(value = "/messages" )
    public ResponseEntity<List<Message>> getMessages(){
        return new ResponseEntity<>( this.messageService.getMessages() , HttpStatus.OK  );
    }

}