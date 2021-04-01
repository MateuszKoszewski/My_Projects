package com.example.auction.controllers;

import com.example.auction.model.exceptions.AppException;
import com.example.auction.model.exceptions.ErrorResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.Objects;

@RestControllerAdvice
public class ExceptionHandlerController  {



    @ExceptionHandler({AppException.class})
    public ResponseEntity<ErrorResponse> handleAppException(AppException appException) {
        return ResponseEntity
                .status(appException.getResponseStatus())
                .body(new ErrorResponse(appException.getMessage(), LocalDateTime.now()));

    }
@ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity<ErrorResponse> handleCustomValidation (MethodArgumentNotValidException exception){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse(Objects.requireNonNull(exception.getBindingResult().getFieldError()).getDefaultMessage(), LocalDateTime.now()));
}


// add to class extends ResponseEntityExceptionHandler
//    @Override
//    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
//        ex.printStackTrace();
//        return new ResponseEntity(HttpStatus.BAD_REQUEST);
//    }
}
