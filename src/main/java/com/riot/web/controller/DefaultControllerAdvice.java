package com.riot.web.controller;

import com.RiotAPIConnection;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
@Slf4j
public class DefaultControllerAdvice {

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<String> nullException(Exception e){
        e.printStackTrace();
        return new ResponseEntity<>("Null", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(RiotAPIConnection.CannotFindData.class)
    public ResponseEntity<String> Exception404(Exception e){
        e.printStackTrace();
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }
}
