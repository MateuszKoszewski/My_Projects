package com.example.auction.model.dto;


import lombok.Data;


import java.time.LocalDateTime;

@Data
public class AddPurchaseResponse {

    private Long Id;

    private GetUserResponse user;

    private GetAuctionResponse auction;

    private double price;

    private LocalDateTime date;
}
