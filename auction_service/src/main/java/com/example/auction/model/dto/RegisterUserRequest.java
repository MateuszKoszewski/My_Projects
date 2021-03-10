package com.example.auction.model.dto;


import lombok.Getter;



@Getter
public class RegisterUserRequest {
    private String emailAddress;
    private String password;
    private String name;
    private String lastName;

    private RegisterAddressRequest address;
}
