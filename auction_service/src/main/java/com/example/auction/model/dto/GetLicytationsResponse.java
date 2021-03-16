package com.example.auction.model.dto;

import lombok.Data;

@Data
public class GetLicytationsResponse {

    private GetAuctionResponse auction;
    private GetUserResponse user;
    double price;
}
