package com.example.auction.model.dto;

import lombok.Getter;

@Getter
public class RegisterAddressRequest {

    private String county;

    private String city;

    private String street;

    private int numberOfHouse;

    private int numberOfFlat;

    private String postCode;
}
