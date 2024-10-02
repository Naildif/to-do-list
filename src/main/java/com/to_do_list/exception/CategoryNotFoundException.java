package com.to_do_list.exception;

public class CategoryNotFoundException extends RuntimeException {
    public CategoryNotFoundException (String message){
        super(message);
    }
}
