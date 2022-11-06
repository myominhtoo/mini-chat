package com.lio.chatserver.controller;

import com.lio.chatserver.model.service.UserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping( name = "/api/v1" )
public class UserController {

    private UserService userService;

    public UserController( UserService userService ){
        this.userService = userService;
    }

}