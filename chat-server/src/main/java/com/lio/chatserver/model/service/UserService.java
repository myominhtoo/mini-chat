package com.lio.chatserver.model.service;

import com.lio.chatserver.model.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("userService")
public class UserService {

    private UserRepo userRepo;

    @Autowired
    public UserService( UserRepo userRepo  ){
        this.userRepo = userRepo;
    }

}