package com.example.auction.model.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
@Data
@Builder
public class GetPurchaseResponse {

    private Long Id;

    private GetUserResponse user;

    private GetAuctionResponse auction;

    private double price;

    private String date;
}
