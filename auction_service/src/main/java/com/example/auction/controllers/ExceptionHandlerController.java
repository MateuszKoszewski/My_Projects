package com.example.auction.controllers;

import com.example.auction.model.exceptions.AppException;
import com.example.auction.model.exceptions.ErrorResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler({AppException.class})
    public ResponseEntity<ErrorResponse> handleAppException(AppException appException) {
        return ResponseEntity
                .status(appException.getResponseStatus())
                .body(new ErrorResponse(appException.getMessage()));
    }
}
