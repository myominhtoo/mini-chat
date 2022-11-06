package com.lio.chatserver.model.service;

import com.lio.chatserver.exception.custom.DuplicateEmailException;
import com.lio.chatserver.model.entity.User;
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

    public User createUser( User user ) throws DuplicateEmailException {
        if( this.isDuplicateUser(user.getEmail()) ){
            throw new DuplicateEmailException("Duplicate Email!");
        }
        return this.userRepo.save(user);
    }

    public User getUserByEmail( String email ){
        return this.userRepo.findByEmail(email);
    }

    private boolean isDuplicateUser( String email  ){
        return this.userRepo.findByEmail(email) != null;
    }

}