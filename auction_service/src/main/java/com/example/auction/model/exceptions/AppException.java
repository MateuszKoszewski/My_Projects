package com.example.auction.model.exceptions;

import lombok.Getter;

@Getter
public class AppException extends RuntimeException{

    private int responseStatus;

    public AppException(AppErrorMessage message, String... params){
        super(String.format(message.getMessageTemplate(), params));
        this.responseStatus = message.getStatus();
    }
}
