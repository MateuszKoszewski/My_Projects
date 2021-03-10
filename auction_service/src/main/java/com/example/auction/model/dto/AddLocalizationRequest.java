package com.example.auction.model.dto;

import lombok.Getter;

@Getter
public class AddLocalizationRequest {

    private String county;
    private String city;
    private String postCode;
}
