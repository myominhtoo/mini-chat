package com.lio.chatserver.exception.handler;

import com.lio.chatserver.exception.custom.DuplicateEmailException;
import com.lio.chatserver.model.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDate;

@RestControllerAdvice
public class AppException {

    @ExceptionHandler({ DuplicateEmailException.class })
    public ResponseEntity<Response<?>> duplicateEmailException(DuplicateEmailException e ){
        Response response = new Response(
                LocalDate.now(),
                HttpStatus.BAD_REQUEST,
                HttpStatus.BAD_REQUEST.value(),
                e.getMessage(),
                false,
                null
                );
        return new ResponseEntity<>( response , response.getHttpStatus() );
    }

}