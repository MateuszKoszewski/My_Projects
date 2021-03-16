package com.example.auction.model.dto;

import lombok.Data;

@Data
public class AddLicytationRequest {

    private Long id;
    private String userEmail;
    double price;
}
