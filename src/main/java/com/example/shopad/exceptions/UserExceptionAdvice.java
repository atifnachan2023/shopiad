package com.example.shopad.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class UserExceptionAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler(UserException.class)
    ResponseEntity<String>  handleEmplyInputFields(UserException exception){

        return new ResponseEntity<>(" Mandatory Input fields are missing", HttpStatus.BAD_REQUEST);
    }




}
