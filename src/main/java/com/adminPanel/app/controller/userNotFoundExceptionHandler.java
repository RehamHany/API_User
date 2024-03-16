package com.adminPanel.app.controller;

import com.adminPanel.app.Exception.Response.UserErrorResponse;
import com.adminPanel.app.Exception.userNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class userNotFoundExceptionHandler {

//    @ExceptionHandler
//    public ResponseEntity<UserErrorResponse> handlerForUserNotFound(userNotFoundException exception){
//        UserErrorResponse errorResponse=new UserErrorResponse();

//        errorResponse.setCode(HttpStatus.NOT_FOUND.value());

//        errorResponse.setMessage(exception.getMessage());

//        errorResponse.setTimestamp(System.currentTimeMillis());

//        return new ResponseEntity<>(errorResponse,HttpStatus.NOT_FOUND);
//    }
}
