package com.lio.chatserver.exception.custom;

public class DuplicateEmailException extends Exception {
    public DuplicateEmailException( String msg ){
        super(msg);
    }
}