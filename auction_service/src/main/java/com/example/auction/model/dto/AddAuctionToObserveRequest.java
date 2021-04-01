package com.example.auction.model.dto;

import lombok.Getter;

import javax.validation.constraints.Size;


@Getter
public class AddAuctionToObserveRequest {
    private Long auctionId;
//    @Size(min=30, max=100, message = "tytuł musi miec 7 do 100")
    private String userEmail;
}
