package com.example.auction.model.exceptions;

import lombok.Getter;

@Getter
public enum AppErrorMessage {

    USER_DOES_NOT_EXISTS("User with username %s not found", 400),
    USER_ALREADY_EXISTS("User with email address %s already exists", 409),
    CATEGORY_ALREADY_EXISTS("category %s already exists", 409);

    private String messageTemplate;
    private int status;

    AppErrorMessage(String messageTemplate, int status){
        this.messageTemplate = messageTemplate;
        this.status = status;
    }
}
