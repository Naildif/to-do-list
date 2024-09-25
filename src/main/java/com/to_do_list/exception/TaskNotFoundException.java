package com.to_do_list.exception;

public class TaskNotFoundException extends RuntimeException {
    public TaskNotFoundException (String message){
        super(message);
    }
}
