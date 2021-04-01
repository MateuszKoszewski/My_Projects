package com.example.auction.model.exceptions;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ErrorResponse {

    private String message;
    private LocalDateTime timeStamp;


    public ErrorResponse(String message, LocalDateTime timeStamp){
        this.message=message;
        this.timeStamp = timeStamp;
    }
}
