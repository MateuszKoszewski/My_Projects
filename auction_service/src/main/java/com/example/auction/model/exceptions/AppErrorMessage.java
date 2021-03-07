package com.example.auction.model.exceptions;

import lombok.Getter;

@Getter
public enum AppErrorMessage {

    USER_DOES_NOT_EXISTS("User with username %s not found", 512),
    USER_ALREADY_EXISTS("User with email address %s already exists", 560);

    private String messageTemplate;
    private int status;

    AppErrorMessage(String messageTemplate, int status){
        this.messageTemplate = messageTemplate;
        this.status = status;
    }
}
