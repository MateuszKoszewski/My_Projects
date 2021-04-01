package com.example.auction.model.dto;

import lombok.Getter;

@Getter
public class AddCategoryResponse {
    private String message;
    private String name;

    public AddCategoryResponse(String message, String name){

        this.message = message;
        this.name = name;
    }
}
