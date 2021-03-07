package com.example.auction.model.dto;

import lombok.Getter;

@Getter
public class AddCategoryResponse {
    private String message;

    public AddCategoryResponse(String message){
        this.message = message;
    }
}
