package com.example.auction.model.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class RegisterUserResponse {

    private String emailAddress;
    private String message;
}
