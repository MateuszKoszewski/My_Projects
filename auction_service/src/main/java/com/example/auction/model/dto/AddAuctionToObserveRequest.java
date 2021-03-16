package com.example.auction.model.dto;

import lombok.Getter;

@Getter
public class AddAuctionToObserveRequest {
    private Long auctionId;
    private String userEmail;
}
