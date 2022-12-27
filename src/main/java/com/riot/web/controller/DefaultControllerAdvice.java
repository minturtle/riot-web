package com.riot.web.controller;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class DefaultControllerAdvice {

    @ExceptionHandler(NullPointerException.class)
    public String nullException(){
        return "null!";
    }

}
