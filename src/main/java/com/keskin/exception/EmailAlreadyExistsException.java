package com.keskin.exception;

public class EmailAlreadyExistsException extends Exception {
    public EmailAlreadyExistsException(){

    }

    public EmailAlreadyExistsException(String msg){
        super(msg);
    }
}
