package com.example.auction.model.dto;

import lombok.Data;

@Data
public class AddPurchaseRequest {
    private String userEmail;
    private Long auctionId;
}
