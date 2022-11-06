package com.lio.chatserver.controller;

import com.lio.chatserver.exception.custom.DuplicateEmailException;
import com.lio.chatserver.exception.handler.AppException;
import com.lio.chatserver.model.Response;
import com.lio.chatserver.model.entity.User;
import com.lio.chatserver.model.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping( value = "/api/v1" )
@CrossOrigin( originPatterns = "*" )
public class UserController extends AppException {
    private UserService userService;

    @Autowired
    public UserController( UserService userService ){
        this.userService = userService;
    }

    @PostMapping( value = "/users" )
    public ResponseEntity<Response<User>> registerUser(@RequestBody User user ) throws DuplicateEmailException {
        User registeredUser = this.userService.createUser(user);
        Response response  = new Response(
                LocalDate.now() ,
                registeredUser == null ? HttpStatus.ACCEPTED : HttpStatus.CREATED,
                registeredUser == null ? HttpStatus.ACCEPTED.value() : HttpStatus.CREATED.value(),
                registeredUser == null ? "Failed to create!" : "Successfully Created!",
                registeredUser != null,
                registeredUser
                );
        return new ResponseEntity<>( response , response.getHttpStatus() );
    }

    @GetMapping( value = "/users" )
    public ResponseEntity<User> getUser( @RequestParam("email") String email ){
        return new ResponseEntity<>( this.userService.getUserByEmail( email ) , HttpStatus.OK );
    }

}