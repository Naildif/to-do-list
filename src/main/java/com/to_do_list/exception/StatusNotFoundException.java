package com.to_do_list.exception;

public class StatusNotFoundException extends RuntimeException{
    public StatusNotFoundException (String message){
        super(message);
    }
}
