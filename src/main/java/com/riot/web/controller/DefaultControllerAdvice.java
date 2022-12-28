package com.riot.web.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Arrays;

@RestControllerAdvice
@Slf4j
public class DefaultControllerAdvice {

    @ExceptionHandler(NullPointerException.class)
    public String nullException(Exception e){
        e.printStackTrace();
        return "null!";
    }

}
